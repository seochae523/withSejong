package sejongZoo.sejongZoo.chat.controller;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sejongZoo.sejongZoo.chat.dto.request.ChatSaveRequestDto;
import sejongZoo.sejongZoo.chat.dto.response.ChatSaveResponseDto;
import sejongZoo.sejongZoo.chat.service.ChatRoomService;
import sejongZoo.sejongZoo.chat.service.ChatService;

@RestController
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    // /pub/message
    @MessageMapping("/message")
    public void sendMessage(ChatSaveRequestDto chatSaveRequestDto){
        chatService.chat(chatSaveRequestDto);
    }
    @PostMapping("/chat")
    @Hidden
    public ResponseEntity<ChatSaveResponseDto> chat(@RequestBody ChatSaveRequestDto chatSaveRequestDto){
        return new ResponseEntity(chatService.chat(chatSaveRequestDto), HttpStatus.OK);
    }
}
