package sejongZoo.sejongZoo.user.service;

import sejongZoo.sejongZoo.common.token.dto.AuthToken;
import sejongZoo.sejongZoo.user.dto.request.LoginRequestDto;
import sejongZoo.sejongZoo.user.dto.request.SignUpRequestDto;
import sejongZoo.sejongZoo.user.dto.response.LogoutResponseDto;
import sejongZoo.sejongZoo.user.dto.response.SignUpResponseDto;

public interface LoginService{
    AuthToken login(sejongZoo.sejongZoo.user.dto.request.LoginRequestDto loginRequestDto);
    LogoutResponseDto logout(String studentId);
    SignUpResponseDto signup(SignUpRequestDto signUpRequestDto);
}
