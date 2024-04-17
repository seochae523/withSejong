package sejongZoo.sejongZoo.user.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sejongZoo.sejongZoo.common.exception.user.*;
import sejongZoo.sejongZoo.user.domain.User;
import sejongZoo.sejongZoo.user.dto.request.UpdateRequestDto;
import sejongZoo.sejongZoo.user.dto.response.DeleteResponseDto;
import sejongZoo.sejongZoo.user.dto.response.UpdateResponseDto;
import sejongZoo.sejongZoo.user.repository.UserRepository;
import sejongZoo.sejongZoo.user.service.UserService;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public Boolean checkStudentId(String studentId) {
        if(studentId == null){
            throw new StudentIdNotFound();
        }
        userRepository.findByStudentId(studentId).ifPresent(x -> {
            throw new DuplicatedStudentId(studentId);
        });

        return true;
    }

    @Override
    public Boolean checkNickname(String nickname) {
        if(nickname == null){
            throw new NicknameNotFound();
        }
        userRepository.findByNickname(nickname).ifPresent(x ->{
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
        userRepository.delete(user);

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

        User user = userRepository.findByStudentId(studentId).orElseThrow(() -> new AccountNotFound(studentId));
        user.updateInfo(major, nickname);
        userRepository.save(user);
        return UpdateResponseDto.builder()
                .studentId(studentId)
                .major(major)
                .nickname(nickname)
                .build();
    }
}
