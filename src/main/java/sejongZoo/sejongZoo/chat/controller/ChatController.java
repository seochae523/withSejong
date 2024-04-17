package sejongZoo.sejongZoo.chat.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.*;
import sejongZoo.sejongZoo.chat.dto.response.ChatRoomResponseDto;
import sejongZoo.sejongZoo.chat.dto.MessageDto;
import sejongZoo.sejongZoo.chat.repository.MemoryChatRoomRepository;
import sejongZoo.sejongZoo.chat.service.ChatRoomService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@SecurityRequirement(name = "Bearer Authentication")
public class ChatController {
    private final MemoryChatRoomRepository chatRoomService;
    private final SimpMessageSendingOperations messageSendingOperations;

    /**
     * 해당 message dynamodb에 저장
     */
    @MessageMapping("/message")
    @Operation(summary = "message 전송",
            description = "채팅방으로 message 전송")
    public void message(MessageDto message){
        messageSendingOperations.convertAndSend("/sub/message/" + message.getRoomId(), message);
    }

    @PostMapping("/chat/room")
    @Operation(summary = "채팅방 생성",
            description = "리턴되는 room 번호로 새로운 채팅방 생성")
    public ChatRoomResponseDto createRoom(@RequestBody ChatRoomResponseDto chatRoomDto){
        ChatRoomResponseDto room = chatRoomService.createRoom(chatRoomDto.getName());
        return room;
    }
    @GetMapping("/chat/rooms")
    @Operation(summary = "모든 채팅방 조회",
            description = "모든 채팅방 조회")
    public List<ChatRoomResponseDto> findAll(){
        List<ChatRoomResponseDto> all = chatRoomService.findAll();
        return all;
    }

}
