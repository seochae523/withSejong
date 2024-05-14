package sejongZoo.sejongZoo.board.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardFindPagingResponseDto {
    private Integer currentPage;
    private Integer totalPages;
    private Long totalElements;
    private List<BoardFindResponseDto> boardFindResponseDtoList;
}
