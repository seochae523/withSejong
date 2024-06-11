package sejongZoo.sejongZoo.fcm.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import okhttp3.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sejongZoo.sejongZoo.fcm.dto.FcmMessageDto;
import sejongZoo.sejongZoo.fcm.dto.FcmSendDto;
import sejongZoo.sejongZoo.fcm.service.FcmService;

import java.io.IOException;

@RestController
@RequestMapping("/fcm")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class FcmController {
    private final FcmService fcmService;

    @PostMapping("/send")
    @Operation(description = "알림 메시지 전송")
    public ResponseEntity<Response> pushMessage(@RequestBody @Validated FcmSendDto fcmSendDto) throws IOException {
        return new ResponseEntity(fcmService.sendMessageTo(fcmSendDto), HttpStatus.OK);
    }
}
