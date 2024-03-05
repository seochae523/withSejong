package sejongZoo.sejongZoo.chat.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.*;
import sejongZoo.sejongZoo.chat.dto.ChatRoomDto;
import sejongZoo.sejongZoo.chat.dto.MessageDto;
import sejongZoo.sejongZoo.chat.repository.MemoryChatRoomRepository;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class ChatController {
    private final MemoryChatRoomRepository chatRoomRepository;
    private final SimpMessageSendingOperations messageSendingOperations;

    @MessageMapping("/message")
    public void message(MessageDto message){
        messageSendingOperations.convertAndSend("/sub/message/" + message.getRoomId(), message);
    }

    @PostMapping("/chat/room")
    public ChatRoomDto createRoom(@RequestBody ChatRoomDto chatRoomDto){
        ChatRoomDto room = chatRoomRepository.createRoom(chatRoomDto.getName());
        return room;
    }
}
