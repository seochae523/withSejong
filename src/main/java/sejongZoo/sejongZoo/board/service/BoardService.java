package sejongZoo.sejongZoo.board.service;

import org.springframework.web.multipart.MultipartFile;
import sejongZoo.sejongZoo.board.dto.request.BoardSaveRequestDto;
import sejongZoo.sejongZoo.board.dto.request.BoardUpdateRequestDto;
import sejongZoo.sejongZoo.board.dto.response.*;

import java.io.IOException;
import java.util.List;

public interface BoardService {
    List<BoardFindResponseDto> findAll(Integer page);

    BoardSaveResponseDto save(List<MultipartFile> multipartFile, BoardSaveRequestDto boardSaveRequestDto) throws IOException;
    BoardUpdateResponseDto update(BoardUpdateRequestDto boardUpdateRequestDto);
    BoardDeleteResponseDto delete(Long id);

    List<BoardFindResponseDto> search(String keyword, Integer page);
}
