package sejongZoo.sejongZoo.user.service;


import sejongZoo.sejongZoo.user.dto.request.UpdateRequestDto;
import sejongZoo.sejongZoo.user.dto.response.DeleteResponseDto;
import sejongZoo.sejongZoo.user.dto.response.UpdateResponseDto;

public interface UserService {
    Boolean checkStudentId(String studentId);
    Boolean checkNickname(String nickname);
    DeleteResponseDto delete(String studentId);
    UpdateResponseDto update(UpdateRequestDto updateRequestDto);
}
