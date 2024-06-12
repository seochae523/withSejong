package sejongZoo.sejongZoo.common.token.controller;



import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;


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
@Tag(name = "refresh")
@SecurityRequirement(name = "Bearer Authentication")
public class RefreshController {
    private final RefreshService refreshService;
    @Operation(description = "해당 url로 요청 시 access token 재발급")
    @PostMapping
    public ResponseEntity refresh(@RequestBody RefreshDto refreshDto) throws ParseException {
        return new ResponseEntity(refreshService.refresh(refreshDto), HttpStatus.OK);
    }
}
