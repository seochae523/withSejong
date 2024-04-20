package sejongZoo.sejongZoo.common.token.controller;



import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.json.simple.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sejongZoo.sejongZoo.common.token.dto.RefreshDto;
import sejongZoo.sejongZoo.common.token.service.RefreshService;


@RestController
@RequiredArgsConstructor
@RequestMapping("/refresh")
@Tag(name = "리프레쉬 컨트롤러")
@SecurityRequirement(name = "Bearer Authentication")
public class RefreshController {
    private final RefreshService refreshService;
    @Operation(summary = "access token 만료시 refresh token을 이용하여 재발급",
                description = "해당 url로 요청 시 access token 재발급")
    @PostMapping
    public ResponseEntity refresh(@RequestBody RefreshDto refreshDto) throws ParseException {
        return new ResponseEntity(refreshService.refresh(refreshDto), HttpStatus.OK);
    }
}
