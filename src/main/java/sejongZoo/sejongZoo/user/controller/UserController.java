package sejongZoo.sejongZoo.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sejongZoo.sejongZoo.common.token.dto.AuthToken;
import sejongZoo.sejongZoo.user.dto.request.ChangePasswordRequestDto;
import sejongZoo.sejongZoo.user.dto.request.EmailSendRequestDto;
import sejongZoo.sejongZoo.user.dto.request.LoginRequestDto;
import sejongZoo.sejongZoo.user.dto.request.UpdateRequestDto;
import sejongZoo.sejongZoo.user.dto.response.*;
import sejongZoo.sejongZoo.user.service.EmailSendService;
import sejongZoo.sejongZoo.user.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping
@SecurityRequirement(name = "Bearer Authentication")
public class UserController {
    private final UserService userService;
    private final EmailSendService emailSendService;
    @GetMapping("/check-nickname")
    @Operation(summary = "닉네임 중복 체크",
            description = "닉네임 중복 체크")
    public ResponseEntity<Boolean> checkNickname(@RequestParam(value = "nickname", required = false) String nickname){
        return new ResponseEntity(userService.checkNickname(nickname), HttpStatus.OK);
    }
    @GetMapping("/check-student-id")
    @Operation(summary = "학번 중복 체크",
            description = "학번 중복 체크")
    public ResponseEntity<CheckStudentIdResponseDto> checkStudentId(@RequestParam(value = "studentId", required = false) String studentId){
        return new ResponseEntity(userService.checkStudentId(studentId), HttpStatus.OK);
    }
    @DeleteMapping("/user/delete")
    @Operation(summary = "유저 삭제",
            description = "해당 유저 삭제")
    public ResponseEntity<DeleteResponseDto> delete(@RequestParam(value = "studentId", required = false) String studentId){
        return new ResponseEntity(userService.delete(studentId), HttpStatus.OK);
    }
    @PutMapping("/user/update")
    @Operation(summary = "유저 정보 업데이트",
            description = "유저 정보 업데이트 - 내부에서 중복확인 하니까 따로 안해도 됨")
    public ResponseEntity<UpdateResponseDto> update(@RequestBody UpdateRequestDto updateRequestDto){
        return new ResponseEntity(userService.update(updateRequestDto), HttpStatus.OK);
    }

    @PutMapping("/user/change-password")
    @Operation(summary = "비번 변경",
        description = "입력된 정보로 비번 변경")
    public ResponseEntity<ChangePasswordResponseDto> updatePassword(@RequestBody ChangePasswordRequestDto changePasswordRequestDto){
        return new ResponseEntity(userService.changePassword(changePasswordRequestDto), HttpStatus.OK);
    }

    @PutMapping("/change-forget-password")
    @Operation(summary = "비번 변경",
            description = "잊어버린 비번 변경")
    public ResponseEntity<ChangePasswordResponseDto> updateForgetPassword(@RequestBody ChangePasswordRequestDto changePasswordRequestDto){
        return new ResponseEntity(userService.changePassword(changePasswordRequestDto), HttpStatus.OK);
    }

    @GetMapping("/find-nickname")
    @Operation(summary = "닉네임 찾기",
            description = "학번 기준으로 닉네임 찾기")
    public ResponseEntity<String> updateForgetPassword(@RequestParam(value = "studentId", required = false) String studentId){
        return new ResponseEntity(userService.findNickname(studentId), HttpStatus.OK);
    }

    @PostMapping("/user/report")
    @Operation(summary = "신고하기",
            description = "게시글 신고")
    public ResponseEntity<EmailSendResponseDto> updateForgetPassword(@RequestBody EmailSendRequestDto emailSendRequestDto){
        return new ResponseEntity(emailSendService.sendEmail(emailSendRequestDto), HttpStatus.CREATED);
    }
}
