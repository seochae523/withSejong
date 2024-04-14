package sejongZoo.sejongZoo.board.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sejongZoo.sejongZoo.board.dto.request.BoardSaveRequestDto;
import sejongZoo.sejongZoo.board.dto.response.BoardFindResponseDto;
import sejongZoo.sejongZoo.board.dto.response.BoardSaveResponseDto;
import sejongZoo.sejongZoo.board.service.BoardService;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/save")
    public ResponseEntity<BoardSaveResponseDto> save(@RequestPart(value = "file", required = false) List<MultipartFile> multipartFile, @RequestPart("request") BoardSaveRequestDto boardSaveRequestDto) throws IOException {
        return new ResponseEntity(boardService.save(multipartFile, boardSaveRequestDto), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<BoardFindResponseDto>> findAll(@RequestParam(value = "page", defaultValue = "0") Integer page){
        return new ResponseEntity(boardService.findAll(page), HttpStatus.OK);
    }
    @GetMapping("/search")
    public ResponseEntity<List<BoardFindResponseDto>> search(@RequestParam(value = "keyword", required = false) String keyword, @RequestParam(value = "page", defaultValue = "0") Integer page){
        return new ResponseEntity(boardService.search(keyword, page), HttpStatus.OK);
    }
}
