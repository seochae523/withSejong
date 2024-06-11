package sejongZoo.sejongZoo.chat.controller;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sejongZoo.sejongZoo.chat.dto.request.ChatSaveRequestDto;
import sejongZoo.sejongZoo.chat.dto.response.ChatFindResponseDto;
import sejongZoo.sejongZoo.chat.dto.response.ChatSaveResponseDto;
import sejongZoo.sejongZoo.chat.service.ChatRoomService;
import sejongZoo.sejongZoo.chat.service.ChatService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
public class ChatController {

    private final ChatService chatService;

    // /pub/message
    @MessageMapping("/message")
    public void sendMessage(@Valid ChatSaveRequestDto chatSaveRequestDto){
        chatService.chat(chatSaveRequestDto);
    }

    @GetMapping("/user/chat/{roomId}")
    @Operation(summary = "채팅 조회",
            description = "해당 room id의 채팅 조회")
    public ResponseEntity<List<ChatFindResponseDto>> findAllChat(@PathVariable
                                                                     @NotNull(message = "Chat Room Id Not Found.")
                                                                     @Positive(message = "Chat Room Id Must Be Positive Value.") Long roomId){
        return new ResponseEntity(chatService.findAll(roomId), HttpStatus.OK);
    }

}
