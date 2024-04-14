package sejongZoo.sejongZoo.user.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sejongZoo.sejongZoo.common.token.dto.AuthToken;
import sejongZoo.sejongZoo.user.dto.UserDto;
import sejongZoo.sejongZoo.user.dto.request.LoginRequestDto;
import sejongZoo.sejongZoo.user.dto.request.SignUpRequestDto;
import sejongZoo.sejongZoo.user.dto.response.SignUpResponseDto;
import sejongZoo.sejongZoo.user.service.LoginService;
import sejongZoo.sejongZoo.user.service.impl.LoginServiceImpl;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<AuthToken> login(@RequestBody LoginRequestDto loginRequestDto){
        return new ResponseEntity(loginService.login(loginRequestDto), HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDto> signUp(@RequestBody SignUpRequestDto signUpRequestDto){
        return new ResponseEntity(loginService.signup(signUpRequestDto), HttpStatus.CREATED);
    }
}
