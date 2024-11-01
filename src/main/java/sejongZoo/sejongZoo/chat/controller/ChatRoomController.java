package sejongZoo.sejongZoo.chat.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sejongZoo.sejongZoo.chat.dto.request.ChatRoomSaveRequestDto;
import sejongZoo.sejongZoo.chat.dto.response.ChatRoomFindResponseDto;
import sejongZoo.sejongZoo.chat.dto.response.ChatRoomSaveResponseDto;
import sejongZoo.sejongZoo.chat.service.ChatRoomService;


import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name="chat room")
public class ChatRoomController {
    private final ChatRoomService chatRoomService;

    @PostMapping("/chat/room")
    @Operation(description = "리턴되는 room 번호로 새로운 채팅방 생성")
    public ResponseEntity<ChatRoomSaveResponseDto> createRoom(@RequestBody @Valid ChatRoomSaveRequestDto chatRoomSaveRequestDto){
        return new ResponseEntity(chatRoomService.save(chatRoomSaveRequestDto), HttpStatus.CREATED);
    }

    @GetMapping("/chat/rooms")
    @Operation(description = "모든 채팅방 조회")
    public ResponseEntity<List<ChatRoomFindResponseDto>> findAll(@NotBlank(message = "Student Id Not Found.")
                                                                     @RequestParam(name="studentId") String studentId){
        return new ResponseEntity(chatRoomService.findByStudentId(studentId), HttpStatus.OK);
    }
    @DeleteMapping("/chat/room/delete/{roomId}")
    @Operation(description = "해당 room id로 채팅방 삭제")
    public ResponseEntity deleteChat(@PathVariable
                                         @NotNull(message = "Chat Room Id Not Found.") Long roomId){
        chatRoomService.delete(roomId);

        return new ResponseEntity(HttpStatus.OK);
    }


}
