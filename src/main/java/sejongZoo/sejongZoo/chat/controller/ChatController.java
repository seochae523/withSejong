package sejongZoo.sejongZoo.chat.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.*;
import sejongZoo.sejongZoo.chat.dto.request.ChatRoomSaveRequestDto;
import sejongZoo.sejongZoo.chat.dto.response.ChatFindResponseDto;
import sejongZoo.sejongZoo.chat.dto.response.ChatRoomFindResponseDto;
import sejongZoo.sejongZoo.chat.dto.request.ChatSaveRequestDto;
import sejongZoo.sejongZoo.chat.dto.response.ChatSaveResponseDto;
import sejongZoo.sejongZoo.chat.service.ChatRoomService;
import sejongZoo.sejongZoo.chat.service.ChatService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@SecurityRequirement(name = "Bearer Authentication")
public class ChatController {
    private final ChatRoomService chatRoomService;

    private final ChatService chatService;
    /**
     * 해당 message dynamodb에 저장
     */
    // /pub/message

    @PostMapping("/chat/room")
    @Operation(summary = "채팅방 생성",
            description = "리턴되는 room 번호로 새로운 채팅방 생성")
    public ResponseEntity<ChatRoomFindResponseDto> createRoom(@RequestBody ChatRoomSaveRequestDto chatRoomSaveRequestDto){
        return new ResponseEntity(chatRoomService.save(chatRoomSaveRequestDto), HttpStatus.CREATED);
    }

    @GetMapping("/chat/rooms")
    @Operation(summary = "모든 채팅방 조회",
            description = "모든 채팅방 조회")
    public ResponseEntity<List<ChatRoomFindResponseDto>> findAll(@RequestParam(name="studentId", required = false) String studentId){
        return new ResponseEntity(chatRoomService.findByStudentId(studentId), HttpStatus.OK);
    }
    @DeleteMapping("/chat/room/delete/{roomId}")
    @Operation(summary = "채팅방 삭제",
            description = "해당 room id로 채팅방 삭제")
    public ResponseEntity deleteChat(@PathVariable Long roomId){
        chatRoomService.delete(roomId);
        return new ResponseEntity(HttpStatus.OK);
    }
    @PostMapping("/chat")
    public ResponseEntity<ChatSaveResponseDto> chat(@RequestBody ChatSaveRequestDto chatSaveRequestDto){
        return new ResponseEntity(chatService.chat(chatSaveRequestDto), HttpStatus.OK);
    }

    @GetMapping("/chat/{roomId}")
    public ResponseEntity<List<ChatFindResponseDto>> findAllChat(@PathVariable Long roomId){
        return new ResponseEntity(chatService.findAll(roomId), HttpStatus.OK);
    }

}
