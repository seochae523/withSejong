package sejongZoo.sejongZoo.user.service;


import sejongZoo.sejongZoo.user.dto.request.LoginRequestDto;
import sejongZoo.sejongZoo.user.dto.request.SignUpRequestDto;
import sejongZoo.sejongZoo.user.dto.response.LoginResponseDto;
import sejongZoo.sejongZoo.user.dto.response.LogoutResponseDto;
import sejongZoo.sejongZoo.user.dto.response.SignUpResponseDto;

public interface LoginService{
    LoginResponseDto login(LoginRequestDto loginRequestDto);
    LogoutResponseDto logout(String studentId);
    SignUpResponseDto signup(SignUpRequestDto signUpRequestDto);
}
