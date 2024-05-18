package sejongZoo.sejongZoo.board.service;


import org.springframework.web.multipart.MultipartFile;
import sejongZoo.sejongZoo.board.dto.request.BoardSaveRequestDto;
import sejongZoo.sejongZoo.board.dto.request.BoardUpdateRequestDto;
import sejongZoo.sejongZoo.board.dto.response.*;

import java.io.IOException;
import java.util.List;

public interface BoardService {
    BoardFindPagingResponseDto findAll(Integer page);

    BoardSaveResponseDto save(List<MultipartFile> multipartFile, BoardSaveRequestDto boardSaveRequestDto) throws IOException;
    BoardUpdateResponseDto update(List<MultipartFile> multipartFile, BoardUpdateRequestDto boardUpdateRequestDto) throws IOException;
    BoardDeleteResponseDto delete(Long id);

    BoardFindPagingResponseDto search(String keyword, Integer page);

    BoardFindPagingResponseDto findByTag(List<String> tags, Integer page);

    BoardPullUpResponseDto pullUp(Long id, String studentId);
}
