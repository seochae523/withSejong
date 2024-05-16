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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import sejongZoo.sejongZoo.board.domain.Board;
import sejongZoo.sejongZoo.board.domain.Image;
import sejongZoo.sejongZoo.board.domain.Tag;
import sejongZoo.sejongZoo.board.dto.request.BoardSaveRequestDto;
import sejongZoo.sejongZoo.board.dto.request.BoardUpdateRequestDto;
import sejongZoo.sejongZoo.board.dto.response.ImageUpdateResponseDto;
import sejongZoo.sejongZoo.board.dto.response.*;
import sejongZoo.sejongZoo.board.repository.BoardRepository;
import sejongZoo.sejongZoo.board.repository.ImageRepository;
import sejongZoo.sejongZoo.board.repository.TagRepository;
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
@Transactional(readOnly = true)
public class BoardServiceImpl implements BoardService{
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final ImageRepository imageRepository;
    private final TagRepository tagRepository;
    private final AmazonS3 amazonS3;
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
    @Value("${cloud.aws.s3.url}")
    private String s3Url;
    @Value("${spring.data.rest.default-page-size}")
    private Integer pageSize;
    @Override
    public BoardFindPagingResponseDto findAll(Integer page) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Board> result = boardRepository.findAllWithUserAndImage(pageable);
        return BoardFindPagingResponseDto.builder()
                .totalElements(result.getTotalElements())
                .totalPages(result.getTotalPages())
                .currentPage(page)
                .boardFindResponseDtoList(result.stream()
                        .filter(x->!x.getDeleted())
                        .map(BoardFindResponseDto::new)
                        .collect(Collectors.toList()))
                .build();
    }



    @Override
    @Transactional
    public BoardSaveResponseDto save(List<MultipartFile> multipartFile, BoardSaveRequestDto boardSaveRequestDto) throws IOException {
        String content = boardSaveRequestDto.getContent();
        String title = boardSaveRequestDto.getTitle();
        String studentId = boardSaveRequestDto.getStudentId();
        Integer price = boardSaveRequestDto.getPrice();
        List<MultipartFile> images = multipartFile;
        Set<Image> imageResult = new HashSet<>();

        if(studentId == null) throw new StudentIdNotFound();
        if(title == null) throw new TitleNotFound();
        if(content == null) throw new ContentNotFound();
        if(price == null) throw new PriceNotFound();

        User user = userRepository.findByStudentId(studentId)
                .orElseThrow(() -> new AccountNotFound(studentId));

        if(images != null) {
            for (MultipartFile image : images) {
                String originalFilename = UUID.randomUUID()+ image.getOriginalFilename();

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
    @Transactional
    public BoardUpdateResponseDto update(List<MultipartFile> multipartFile, BoardUpdateRequestDto boardUpdateRequestDto) throws IOException {
        Long id = boardUpdateRequestDto.getId();
        String title = boardUpdateRequestDto.getTitle();
        String content = boardUpdateRequestDto.getContent();
        Integer price = boardUpdateRequestDto.getPrice();
        String studentId = boardUpdateRequestDto.getStudentId();
        List<Long> imageIds = boardUpdateRequestDto.getImageId();
        List<Long> tagIds = boardUpdateRequestDto.getTagId();
        List<String> newTagCategories = boardUpdateRequestDto.getNewTagCategory();
        List<MultipartFile> images = multipartFile;
        List<ImageUpdateResponseDto> imageUpdateResponseDto = new ArrayList<>();
        List<TagUpdateResponseDto> tagUpdateResponseDto = new ArrayList<>();
        Set<Image> imageResult = new HashSet<>();
        Set<Tag> tagResult = new HashSet<>();

        if(id == null) throw new BoardIdNotFound();
        if(title == null) throw new TitleNotFound();
        if(content == null) throw new ContentNotFound();
        if(price == null) throw new PriceNotFound();
        if(studentId == null) throw new StudentIdNotFound();


        // 1. 이미지 추가
        // 2. 이미지 수정 -> 얘는 근데 삭제하고 다시 올리는거잖슴 따라서 필요 없다
        // 3. 이미지 삭제
        // --------------- 이미지 업데이트 시작
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new BoardNotFound(id));

        for (Long imageId : imageIds) {
            Image image = imageRepository.
                    findById(imageId).orElseThrow(() -> new ImageNotFound(imageId));
            imageRepository.delete(image);
            String url = image.getUrl();
            amazonS3.deleteObject(bucket, url.split("/")[3]);
            imageUpdateResponseDto.add(ImageUpdateResponseDto.builder()
                    .id(image.getId())
                    .url(url)
                    .status("deleted").build());

        }

        if(images != null) {
            for (MultipartFile image : images) {
                String originalFilename =  UUID.randomUUID() + image.getOriginalFilename();

                ObjectMetadata metadata = new ObjectMetadata();

                metadata.setContentLength(image.getSize());
                metadata.setContentType(image.getContentType());

                amazonS3.putObject(bucket, originalFilename, image.getInputStream(), metadata);
                String url = amazonS3.getUrl(bucket, originalFilename).toString();

                Image save = imageRepository.save(Image.builder()
                        .url(url)
                        .build());

                imageResult.add(save);
                imageUpdateResponseDto.add(ImageUpdateResponseDto.builder()
                                .id(save.getId())
                                .status("added")
                                .url(url)
                                .build());
            }
        }
        // --------------- 이미지 업데이트 끝

        // --------------- 테그 업데이트 시작
        for (Long tagId : tagIds) {
            Tag tag = tagRepository.findById(tagId).orElseThrow(() -> new TagNotFound(tagId));
            tagRepository.delete(tag);
            tagUpdateResponseDto.add(TagUpdateResponseDto.builder()
                            .id(tag.getId())
                            .category(tag.getCategory())
                            .status("deleted")
                            .build());

        }
        for (String newTagCategory : newTagCategories) {
            Tag save = tagRepository.save(Tag.builder()
                    .category(newTagCategory)
                    .board(board)
                    .build());

            tagResult.add(save);

            tagUpdateResponseDto.add(TagUpdateResponseDto.builder()
                            .id(save.getId())
                            .status("added")
                            .category(newTagCategory)
                            .build());
        }
        board.updateBoard(boardUpdateRequestDto, imageResult, tagResult);

        boardRepository.save(board);

        return BoardUpdateResponseDto.builder()
                .tag(tagUpdateResponseDto)
                .image(imageUpdateResponseDto)
                .content(content)
                .price(price)
                .title(title)
                .build();
    }

    @Override
    @Transactional
    public BoardDeleteResponseDto delete(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new BoardNotFound(id));

        board.setDeleted(true);
        boardRepository.save(board);
        return new BoardDeleteResponseDto(board);

    }

    @Override
    public BoardFindPagingResponseDto search(String keyword, Integer page){
        if(keyword == null) throw new KeywordNotFound();

        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Board> result = boardRepository.searchWithKeyword(keyword, pageable);

        return BoardFindPagingResponseDto.builder()
                .totalElements(result.getTotalElements())
                .totalPages(result.getTotalPages())
                .currentPage(page)
                .boardFindResponseDtoList(result.stream()
                        .filter(x->!x.getDeleted())
                        .map(BoardFindResponseDto::new)
                        .collect(Collectors.toList()))
                .build();
    }

    @Override
    public BoardFindPagingResponseDto findByTag(List<String> tags, Integer page) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Board> result = boardRepository.findByTag(tags, pageable);

        return BoardFindPagingResponseDto.builder()
                .totalElements(result.getTotalElements())
                .totalPages(result.getTotalPages())
                .currentPage(page)
                .boardFindResponseDtoList(result.stream()
                        .filter(x->!x.getDeleted())
                        .map(BoardFindResponseDto::new)
                        .collect(Collectors.toList()))
                .build();
    }
}
