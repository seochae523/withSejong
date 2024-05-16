package sejongZoo.sejongZoo.user.service.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sejongZoo.sejongZoo.common.exception.user.*;
import sejongZoo.sejongZoo.user.domain.User;
import sejongZoo.sejongZoo.user.dto.request.ChangePasswordRequestDto;
import sejongZoo.sejongZoo.user.dto.request.UpdateRequestDto;
import sejongZoo.sejongZoo.user.dto.response.ChangePasswordResponseDto;
import sejongZoo.sejongZoo.user.dto.response.CheckStudentIdResponseDto;
import sejongZoo.sejongZoo.user.dto.response.DeleteResponseDto;
import sejongZoo.sejongZoo.user.dto.response.UpdateResponseDto;
import sejongZoo.sejongZoo.user.repository.UserRepository;
import sejongZoo.sejongZoo.user.service.UserService;

import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public CheckStudentIdResponseDto checkStudentId(String studentId) {
        /**
         * 1. 가입 되고 deleted false면 throw
         * 2. 가입 됐는데 deleted true면 가입
         * 3. 가입 안됐으면 true 리턴
         */
        if(studentId == null) throw new StudentIdNotFound();

        Optional<User> result = userRepository.findByStudentId(studentId);

        if(result.isEmpty()){
            return CheckStudentIdResponseDto.builder()
                    .isDeleted(true)
                    .isSigned(false)
                    .build();
        }
        else{
            return CheckStudentIdResponseDto.builder()
                    .isDeleted(result.get().getDeleted())
                    .isSigned(true)
                    .build();
        }
    }

    @Override
    public Boolean checkNickname(String nickname) {
        if(nickname == null) throw new NicknameNotFound();

        userRepository.findByNickname(nickname)
                .ifPresent(x ->{
            throw new DuplicatedNickname(nickname);
        });

        return true;
    }

    private void checkNickname(String studentId, String nickname){
        userRepository.findByNickname(nickname).ifPresent(x -> {
            if(!x.getStudentId().equals(studentId)){
                throw new DuplicatedNickname(nickname);
            }
        });
    }

    @Override
    @Transactional
    public DeleteResponseDto delete(String studentId) {
        if(studentId == null) throw new StudentIdNotFound();

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
    @Transactional
    public UpdateResponseDto update(UpdateRequestDto updateRequestDto) {
        String studentId = updateRequestDto.getStudentId();
        String major = updateRequestDto.getMajor();
        String nickname = updateRequestDto.getNickname();

        if(studentId == null) throw new StudentIdNotFound();
        if(major == null) throw new MajorNotFound();
        if(nickname == null) throw new NicknameNotFound();

        this.checkNickname(studentId, nickname);

        User user = userRepository.findByStudentId(studentId)
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
    @Transactional
    public ChangePasswordResponseDto changePassword(ChangePasswordRequestDto changePasswordRequestDto) {
        String studentId = changePasswordRequestDto.getStudentId();
        String password = changePasswordRequestDto.getPassword();

        if(studentId == null) throw new StudentIdNotFound();
        if(password == null) throw new PasswordNotFound();

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

    @Override
    public String findNickname(String studentId) {
        if(studentId == null) throw new StudentIdNotFound();

        User user = userRepository.findByStudentId(studentId)
                .orElseThrow(() -> new AccountNotFound(studentId));

        return user.getNickname();

    }
}
