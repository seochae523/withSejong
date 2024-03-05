package sejongZoo.sejongZoo.user.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import sejongZoo.sejongZoo.common.token.dto.AuthToken;
import sejongZoo.sejongZoo.user.domain.User;
import sejongZoo.sejongZoo.user.dto.LoginDto;
import sejongZoo.sejongZoo.user.dto.UserDto;
import sejongZoo.sejongZoo.user.dto.request.LoginRequestDto;
import sejongZoo.sejongZoo.user.dto.request.SignUpRequestDto;
import sejongZoo.sejongZoo.user.dto.response.SignUpResponseDto;

public interface LoginService{
    AuthToken login(LoginRequestDto loginRequestDto);
    LoginDto logout(LoginDto loginDto);
    SignUpResponseDto signup(SignUpRequestDto signUpRequestDto);
}
