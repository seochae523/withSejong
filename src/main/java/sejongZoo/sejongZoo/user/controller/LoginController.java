package sejongZoo.sejongZoo.user.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sejongZoo.sejongZoo.common.token.dto.AuthToken;
import sejongZoo.sejongZoo.user.dto.request.LoginRequestDto;
import sejongZoo.sejongZoo.user.dto.request.SignUpRequestDto;
import sejongZoo.sejongZoo.user.dto.response.LogoutResponseDto;
import sejongZoo.sejongZoo.user.dto.response.SignUpResponseDto;
import sejongZoo.sejongZoo.user.service.LoginService;

@RestController
@RequiredArgsConstructor
@RequestMapping
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "login")
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/login")
    @Operation(description = "로그인")
    public ResponseEntity<AuthToken> login(@RequestBody @Valid LoginRequestDto loginRequestDto){
        return new ResponseEntity(loginService.login(loginRequestDto), HttpStatus.OK);
    }

    @PostMapping("/signup")
    @Operation(description = "회원가입")
    public ResponseEntity<SignUpResponseDto> signUp(@RequestBody @Valid SignUpRequestDto signUpRequestDto){
        return new ResponseEntity(loginService.signup(signUpRequestDto), HttpStatus.CREATED);
    }

    @GetMapping("/user/logout")
    @Operation(description = "로그아웃 하면 refresh token 파기")
    public ResponseEntity<LogoutResponseDto> logout(@RequestParam(value = "studentId")
                                                        @NotBlank(message = "Student Id Not Found.") String studentId){
        return new ResponseEntity(loginService.logout(studentId), HttpStatus.OK);
    }
}
