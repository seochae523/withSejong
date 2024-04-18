package sejongZoo.sejongZoo.user.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sejongZoo.sejongZoo.common.exception.user.*;
import sejongZoo.sejongZoo.user.domain.User;
import sejongZoo.sejongZoo.user.dto.request.ChangePasswordRequestDto;
import sejongZoo.sejongZoo.user.dto.request.UpdateRequestDto;
import sejongZoo.sejongZoo.user.dto.response.ChangePasswordResponseDto;
import sejongZoo.sejongZoo.user.dto.response.DeleteResponseDto;
import sejongZoo.sejongZoo.user.dto.response.UpdateResponseDto;
import sejongZoo.sejongZoo.user.repository.UserRepository;
import sejongZoo.sejongZoo.user.service.UserService;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public Boolean checkStudentId(String studentId) {
        if(studentId == null){
            throw new StudentIdNotFound();
        }
        userRepository.findByStudentId(studentId)
                .filter(user -> !user.getDeleted())
                .ifPresent(user -> {
            throw new DuplicatedStudentId(studentId);
        });

        return true;
    }

    @Override
    public Boolean checkNickname(String nickname) {
        if(nickname == null){
            throw new NicknameNotFound();
        }
        userRepository.findByNickname(nickname).filter(user -> !user.getDeleted())
                .ifPresent(x ->{
            throw new DuplicatedNickname(nickname);
        });

        return true;
    }

    @Override
    public DeleteResponseDto delete(String studentId) {
        if(studentId == null){
            throw new StudentIdNotFound();
        }

        User user = userRepository.findByStudentId(studentId).orElseThrow(() -> new AccountNotFound(studentId));

        // soft delete
        user.setDeleted(true);
        userRepository.save(user);

        return DeleteResponseDto.builder()
                .name(user.getName())
                .studentId(studentId)
                .build();
    }

    @Override
    public UpdateResponseDto update(UpdateRequestDto updateRequestDto) {
        String studentId = updateRequestDto.getStudentId();
        String major = updateRequestDto.getMajor();
        String nickname = updateRequestDto.getNickname();

        if(studentId == null){
            throw new StudentIdNotFound();
        }
        if(major == null){
            throw new MajorNotFound();
        }
        if(nickname == null){
            throw new NicknameNotFound();
        }

        checkNickname(nickname);

        User user = userRepository.findByStudentId(studentId).filter(x->!x.getDeleted())
                .orElseThrow(() -> new AccountNotFound(studentId));

        user.updateInfo(major, nickname);
        userRepository.save(user);

        return UpdateResponseDto.builder()
                .studentId(studentId)
                .major(major)
                .nickname(nickname)
                .build();
    }

    @Override
    public ChangePasswordResponseDto changePassword(ChangePasswordRequestDto changePasswordRequestDto) {
        String studentId = changePasswordRequestDto.getStudentId();
        String password = changePasswordRequestDto.getPassword();

        if(studentId == null){
            throw new StudentIdNotFound();
        }
        if(password == null){
            throw new PasswordNotFound();
        }

        User user = userRepository.
                findByStudentId(studentId).
                filter(x -> !x.getDeleted()).
                orElseThrow(() -> new AccountNotFound(studentId));

        user.updatePassword(password);
        user.hashPassword(passwordEncoder);
        userRepository.save(user);

        return ChangePasswordResponseDto.builder()
                .nickname(user.getNickname())
                .studentId(studentId)
                .build();
    }
}
