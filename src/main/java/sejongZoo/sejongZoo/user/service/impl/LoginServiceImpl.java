package sejongZoo.sejongZoo.user.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sejongZoo.sejongZoo.common.emun.Role;
import sejongZoo.sejongZoo.common.exception.user.*;
import sejongZoo.sejongZoo.common.token.AuthTokenProvider;
import sejongZoo.sejongZoo.common.token.dto.AuthToken;
import sejongZoo.sejongZoo.user.domain.User;
import sejongZoo.sejongZoo.user.dto.request.LoginRequestDto;
import sejongZoo.sejongZoo.user.dto.request.SignUpRequestDto;
import sejongZoo.sejongZoo.user.dto.response.LoginResponseDto;
import sejongZoo.sejongZoo.user.dto.response.LogoutResponseDto;
import sejongZoo.sejongZoo.user.dto.response.SignUpResponseDto;
import sejongZoo.sejongZoo.user.repository.UserRepository;
import sejongZoo.sejongZoo.user.service.LoginService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LoginServiceImpl implements LoginService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthTokenProvider authTokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @Override
    @Transactional
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        String studentId = loginRequestDto.getStudentId();

        User user = userRepository.findByStudentId(studentId)
                .filter(x -> !x.getDeleted())
                .orElseThrow(() -> new AccountNotFound(studentId));

        String role = user.getRole();
        List<String> roles = new ArrayList<>();
        for (String s : role.split(",")) {
            roles.add(s);
        }

        String password = loginRequestDto.getPassword();
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(studentId, password);
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        AuthToken authToken = authTokenProvider.generateToken(authentication, roles);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        user.setRefreshToken(authToken.getRefreshToken());
        userRepository.save(user);

        return LoginResponseDto.builder()
                .authToken(authToken)
                .major(user.getMajor())
                .nickname(user.getNickname())
                .studentId(user.getStudentId())
                .build();

    }




    @Override
    @Transactional
    public SignUpResponseDto signup(SignUpRequestDto signUpRequestDto) {
        String studentId = signUpRequestDto.getStudentId();

        Optional<User> result = userRepository.findByStudentId(studentId);

        if(result.isEmpty()){
            User user = signUpRequestDto.toEntity();

            user.setRole(Role.USER);
            user.hashPassword(passwordEncoder);

            userRepository.save(user);


            return SignUpResponseDto.builder()
                    .name(user.getName())
                    .studentId(user.getStudentId())
                    .build();
        }
        else{
            User user = result.get();
            if(user.getDeleted()){
                user.setDeleted(false);
                userRepository.save(user);


                return SignUpResponseDto.builder()
                        .name(user.getName())
                        .studentId(user.getStudentId())
                        .build();
            }
            else{
                throw new AccountAlreadyExist(studentId);
            }
        }
    }

    @Override
    @Transactional
    public LogoutResponseDto logout(String studentId) {
        User user = userRepository.findByStudentId(studentId)
                .orElseThrow(() -> new AccountNotFound(studentId));

        user.setRefreshToken(null);
        userRepository.save(user);

        return new LogoutResponseDto(user);
    }



}
