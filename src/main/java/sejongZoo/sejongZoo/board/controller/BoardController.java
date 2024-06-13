package sejongZoo.sejongZoo.board.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sejongZoo.sejongZoo.board.dto.request.BoardSaveRequestDto;
import sejongZoo.sejongZoo.board.dto.request.BoardUpdateRequestDto;
import sejongZoo.sejongZoo.board.dto.response.*;
import sejongZoo.sejongZoo.board.service.BoardService;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/board")
@Tag(name = "board")
@SecurityRequirement(name = "Bearer Authentication")
@Validated
public class BoardController {
    private final BoardService boardService;

    @Operation(description = "게시글을 form-data 타입을 통해 저장 (json x)")
    @PostMapping("/save")
    @Parameters({
            @Parameter(name="file", description = "파일 첨부"),
            @Parameter(name="request", description = "게시글에 필요한 리퀘스트", required = true)
    })
    public ResponseEntity<BoardSaveResponseDto> save(@RequestPart(value = "file", required = false) List<MultipartFile> multipartFile,
                                                     @RequestPart(value = "request", required = false) @Valid BoardSaveRequestDto boardSaveRequestDto) throws IOException {
        return new ResponseEntity(boardService.save(multipartFile, boardSaveRequestDto), HttpStatus.OK);
    }
    @Operation(description = "게시글을 10개씩 조회 (페이지 시작 0부터)")
    @GetMapping
    @Parameter(name = "page", description = "페이지 번호 기본 값 0")
    public ResponseEntity<BoardFindPagingResponseDto> findAll(@RequestParam(value = "page", defaultValue = "0") Integer page){
        return new ResponseEntity(boardService.findAll(page), HttpStatus.OK);
    }
    @Operation(description = "게시글을 keyword를 통해 검색 마찬가지로 10개씩 검색 결과 나옴 (페이지 시작 0부터)")
    @GetMapping("/search")
    @Parameters({
            @Parameter(name = "keyword", description = "검색 키워드", required = true),
            @Parameter(name = "page", description = "페이지 번호 기본 값 0")
    })
    public ResponseEntity<BoardFindPagingResponseDto> search(@NotBlank(message = "Keyword Not Found.") @RequestParam(value = "keyword") String keyword,
                                                             @RequestParam(value = "page", defaultValue = "0") Integer page){
        return new ResponseEntity(boardService.search(keyword, page), HttpStatus.OK);
    }
    @Operation(description = "게시글 업데이트")
    @PutMapping("/update")
    @Parameters({
            @Parameter(name = "file", description = "파일임 url xx"),
            @Parameter(name = "request", description = "업데이트에 필요한 json 데이터들")
    })
    public ResponseEntity<BoardUpdateResponseDto> update(@RequestPart(value = "file", required = false) List<MultipartFile> multipartFile,
                                                         @RequestPart(value = "request", required = false) @Valid BoardUpdateRequestDto boardUpdateRequestDto) throws IOException {
        return new ResponseEntity(boardService.update(multipartFile, boardUpdateRequestDto), HttpStatus.OK);
    }
    @Operation(description = "게시글 끌어올리기")
    @PutMapping("/pull-up")
    @Parameters({
            @Parameter(name = "studentId", description = "학번"),
            @Parameter(name = "boardId", description = "게시판 id")
    })
    public ResponseEntity<BoardPullUpResponseDto> pullUp(@NotNull(message = "Board Id Not Found.")
                                                             @Positive(message = "Board Id Must Be Positive Value.") @RequestParam(value = "boardId") Long id,
                                                         @NotBlank(message = "Student Id Not Found.") @RequestParam(value = "studentId") String studentId) {
        return new ResponseEntity(boardService.pullUp(id, studentId), HttpStatus.OK);
    }

    @Operation(description = "테그로 게시글 검색")
    @GetMapping("/find-by-tag")
    @Parameters({
            @Parameter(name = "tags", description = "검색 태그 (전필, 전선 등등) 리스트로 주세요 ex) tags=전필,전선,교양", required = true),
            @Parameter(name = "page", description = "페이지 번호 기본 값 0")
    })
    public ResponseEntity<BoardUpdateResponseDto> findByTags(@NotNull(message = "Tag Not Found.") @RequestParam(value = "tags") List<String> tags,
                                                         @RequestParam(value = "page", defaultValue = "0")
                                                         @PositiveOrZero(message = "Page Must Be Positive Or Zero Value.")Integer page){
        return new ResponseEntity(boardService.findByTag(tags, page), HttpStatus.OK);
    }

    @Operation(description = "status 업데이트 판매 중 = 0, 예약 중 = 1, 판매 완료 = 2")
    @PutMapping("/status")
    @Parameters({
            @Parameter(name = "boardId", description = "개시판 id", required = true),
            @Parameter(name = "status", description = "변경할 게시판 상태")
    })
    public ResponseEntity<BoardFindResponseDto> updateStatus(@RequestParam(value = "boardId")
                                                                 @Positive(message = "Board Id Must Be Positive Value.")
                                                                 @NotNull(message = "Board Id Not Found.") Long boardId,

                                                             @RequestParam(value = "status", defaultValue = "0")
                                                             @PositiveOrZero(message = "Status Must Be Positive Or Zero Value.") Integer status){
        return new ResponseEntity(boardService.updateStatus(boardId, status), HttpStatus.OK);
    }

    @Operation(description = "내 판매 내역 조회")
    @GetMapping("/history")
    @Parameters({
            @Parameter(name = "studentId", description = "학번", required = true),
            @Parameter(name = "page", description = "페이지 번호 기본 값 0")
    })
    public ResponseEntity<BoardFindPagingResponseDto> findMySalesHistory(@RequestParam(value = "studentId", required = false)
                                                                             @NotBlank(message = "Student Id Not Found.") String studentId,
                                                                   @RequestParam(value = "page", defaultValue = "0")
                                                                   @PositiveOrZero(message = "Page Must Be Positive Or Zero Value.") Integer page){
        return new ResponseEntity(boardService.findMySalesHistory(page, studentId), HttpStatus.OK);
    }

    @Operation(description = "삭제")
    @DeleteMapping
    @Parameters({
            @Parameter(name = "boardId", description = "게시판 id", required = true)
    })
    public ResponseEntity<BoardDeleteResponseDto> delete(@RequestParam(value = "boardId")
                                                                         @NotNull(message = "Board Id Not Found.")
                                                                            @PositiveOrZero(message = "Board Id Must Be Positive Or Zero Value.") Long boardId){
        return new ResponseEntity(boardService.delete(boardId), HttpStatus.OK);
    }
}
