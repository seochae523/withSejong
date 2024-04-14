package sejongZoo.sejongZoo.chat.controller;

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
public class ChatController {
    private final MemoryChatRoomRepository chatRoomService;
    private final SimpMessageSendingOperations messageSendingOperations;

    /**
     * 해당 message dynamodb에 저장
     */
    @MessageMapping("/message")
    public void message(MessageDto message){
        messageSendingOperations.convertAndSend("/sub/message/" + message.getRoomId(), message);
    }

    @PostMapping("/chat/room")
    public ChatRoomResponseDto createRoom(@RequestBody ChatRoomResponseDto chatRoomDto){
        ChatRoomResponseDto room = chatRoomService.createRoom(chatRoomDto.getName());
        return room;
    }
    @GetMapping("/chat/rooms")
    public List<ChatRoomResponseDto> findAll(){
        List<ChatRoomResponseDto> all = chatRoomService.findAll();
        return all;
    }

}
