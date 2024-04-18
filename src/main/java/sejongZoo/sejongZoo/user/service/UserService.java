package sejongZoo.sejongZoo.user.service;


import sejongZoo.sejongZoo.user.dto.request.ChangePasswordRequestDto;
import sejongZoo.sejongZoo.user.dto.request.UpdateRequestDto;
import sejongZoo.sejongZoo.user.dto.response.ChangePasswordResponseDto;
import sejongZoo.sejongZoo.user.dto.response.DeleteResponseDto;
import sejongZoo.sejongZoo.user.dto.response.UpdateResponseDto;

public interface UserService {
    Boolean checkStudentId(String studentId);
    Boolean checkNickname(String nickname);
    DeleteResponseDto delete(String studentId);
    UpdateResponseDto update(UpdateRequestDto updateRequestDto);

    ChangePasswordResponseDto changePassword(ChangePasswordRequestDto changePasswordRequestDto);
}
