package sejongZoo.sejongZoo.board.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sejongZoo.sejongZoo.board.domain.Board;
import sejongZoo.sejongZoo.board.domain.Image;
import sejongZoo.sejongZoo.board.domain.Tag;
import sejongZoo.sejongZoo.board.dto.request.BoardSaveRequestDto;
import sejongZoo.sejongZoo.board.dto.request.BoardUpdateRequestDto;
import sejongZoo.sejongZoo.board.dto.request.ImageUpdateResponseDto;
import sejongZoo.sejongZoo.board.dto.response.*;
import sejongZoo.sejongZoo.board.repository.BoardRepository;
import sejongZoo.sejongZoo.board.repository.ImageRepository;
import sejongZoo.sejongZoo.common.exception.board.*;
import sejongZoo.sejongZoo.common.exception.user.AccountNotFound;
import sejongZoo.sejongZoo.common.exception.user.StudentIdNotFound;
import sejongZoo.sejongZoo.user.domain.User;
import sejongZoo.sejongZoo.user.repository.UserRepository;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardServiceImpl implements BoardService{
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final ImageRepository imageRepository;
    private final AmazonS3 amazonS3;
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
    @Value("${cloud.aws.s3.url}")
    private String s3Url;
    @Value("${spring.data.rest.default-page-size}")
    private Integer pageSize;
    @Override
    public List<BoardFindResponseDto> findAll(Integer page) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Board> result = boardRepository.findAllWithUserAndImage(pageable);

        return result.stream()
                .filter(x->!x.getDeleted())
                .map(BoardFindResponseDto::new)
                .collect(Collectors.toList());
    }



    @Override
    public BoardSaveResponseDto save(List<MultipartFile> multipartFile, BoardSaveRequestDto boardSaveRequestDto) throws IOException {
        String content = boardSaveRequestDto.getContent();
        String title = boardSaveRequestDto.getTitle();
        String studentId = boardSaveRequestDto.getStudentId();
        Integer price = boardSaveRequestDto.getPrice();
        List<MultipartFile> images = multipartFile;
        Set<Image> imageResult = new HashSet<>();
        if(studentId == null){
            throw new StudentIdNotFound();
        }
        if(title == null){
            throw new TitleNotFound();
        }
        if(content == null){
            throw new ContentNotFound();
        }
        if(price == null){
            throw new PriceNotFound();
        }

        User user = userRepository.findByStudentId(studentId)
                .orElseThrow(() -> new AccountNotFound(studentId));
        if(images != null) {
            for (MultipartFile image : images) {
                String originalFilename = image.getOriginalFilename() + UUID.randomUUID();

                ObjectMetadata metadata = new ObjectMetadata();

                metadata.setContentLength(image.getSize());
                metadata.setContentType(image.getContentType());

                amazonS3.putObject(bucket, originalFilename, image.getInputStream(), metadata);
                String url = amazonS3.getUrl(bucket, originalFilename).toString();

                Image save = imageRepository.save(Image.builder()
                        .url(url)
                        .build());

                imageResult.add(save);
            }
        }


            Board save = boardRepository.save(boardSaveRequestDto.toEntity(user, imageResult));
            return BoardSaveResponseDto.builder()
                    .studentId(user.getStudentId())
                    .content(save.getContent())
                    .title(save.getTitle()).build();


    }

    @Override
    public BoardUpdateResponseDto update(BoardUpdateRequestDto boardUpdateRequestDto) {
        // 1. 유저 검증
        // 2. board 찾기
        // 3. 이미지 찾기
        Long id = boardUpdateRequestDto.getId();
        String title = boardUpdateRequestDto.getTitle();
        String content = boardUpdateRequestDto.getContent();
        Integer price = boardUpdateRequestDto.getPrice();
        List<ImageUpdateResponseDto> image = boardUpdateRequestDto.getImageUpdateResponseDto();
        if(id == null){
            throw new BoardIdNotFound();
        }
        if(title == null){
            throw new TitleNotFound();
        }
        if(content == null){
            throw new ContentNotFound();
        }
        if(price == null){
            throw new PriceNotFound();
        }
        // 1. 이미지 추가
        // 2. 이미지 수정
        // 3. 이미지 삭제
        for (ImageUpdateResponseDto imageUpdateResponseDto : image) {
            Long imageId = imageUpdateResponseDto.getId();
            String imageUrl = imageUpdateResponseDto.getUrl();
            if(imageId == null){
                throw new ImageIdNotFound();
            }
            if(imageUrl == null){
                throw new ImageUrlNotFound();
            }
            imageRepository.findById(imageId).orElseThrow(()->new ImageNotFound(imageId));
        }
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new BoardNotFound(id));

        return null;
    }

    @Override
    public BoardDeleteResponseDto delete(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new BoardNotFound(id));

        board.setDeleted(true);
        boardRepository.save(board);
        return new BoardDeleteResponseDto(board);

    }

    @Override
    public List<BoardFindResponseDto> search(String keyword, Integer page){
        if(keyword == null){
            throw new KeywordNotFound();
        }
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Board> result = boardRepository.searchWithKeyword(keyword, pageable);

        return result.stream()
                .map(BoardFindResponseDto::new)
                .collect(Collectors.toList());
    }
}
