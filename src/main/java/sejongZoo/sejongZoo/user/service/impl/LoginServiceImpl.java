package sejongZoo.sejongZoo.user.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sejongZoo.sejongZoo.common.emun.Role;
import sejongZoo.sejongZoo.common.exception.user.AccountNotFound;
import sejongZoo.sejongZoo.common.exception.user.StudentIdNotFound;
import sejongZoo.sejongZoo.common.token.AuthTokenProvider;
import sejongZoo.sejongZoo.common.token.dto.AuthToken;
import sejongZoo.sejongZoo.user.domain.User;
import sejongZoo.sejongZoo.user.dto.request.LoginRequestDto;
import sejongZoo.sejongZoo.user.dto.request.SignUpRequestDto;
import sejongZoo.sejongZoo.user.dto.response.SignUpResponseDto;
import sejongZoo.sejongZoo.user.repository.UserRepository;
import sejongZoo.sejongZoo.user.service.LoginService;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthTokenProvider authTokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @Override
    public AuthToken login(sejongZoo.sejongZoo.user.dto.request.LoginRequestDto loginRequestDto) {
        String studentId = loginRequestDto.getStudentId();
        if(studentId == null){
            throw new StudentIdNotFound();
        }
        User user = userRepository.findByStudentId(studentId)
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

        return authToken;
    }




    @Override
    public SignUpResponseDto signup(SignUpRequestDto signUpRequestDto) {
        User user = signUpRequestDto.toEntity();

        user.setRole(Role.USER);
        user.hashPassword(passwordEncoder);

        userRepository.save(user);

        return SignUpResponseDto.builder()
                .name(user.getName())
                .studentId(user.getStudentId())
                .build();
    }

    @Override
    public LoginRequestDto logout(LoginRequestDto loginRequestDto) {
        return null;
    }



}
