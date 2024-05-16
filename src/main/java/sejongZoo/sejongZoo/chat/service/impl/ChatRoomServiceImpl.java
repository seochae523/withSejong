package sejongZoo.sejongZoo.chat.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sejongZoo.sejongZoo.chat.domain.ChatRoom;
import sejongZoo.sejongZoo.chat.dto.request.ChatRoomSaveRequestDto;
import sejongZoo.sejongZoo.chat.dto.response.ChatRoomFindResponseDto;
import sejongZoo.sejongZoo.chat.dto.response.ChatRoomSaveResponseDto;
import sejongZoo.sejongZoo.chat.repository.ChatRoomRepository;
import sejongZoo.sejongZoo.chat.service.ChatRoomService;
import sejongZoo.sejongZoo.common.exception.chat.ChatRoomNotFound;
import sejongZoo.sejongZoo.common.exception.chat.RoomIdNotFound;
import sejongZoo.sejongZoo.common.exception.user.AccountNotFound;
import sejongZoo.sejongZoo.common.exception.user.StudentIdNotFound;
import sejongZoo.sejongZoo.user.domain.User;
import sejongZoo.sejongZoo.user.repository.UserRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class ChatRoomServiceImpl implements ChatRoomService {
    private final UserRepository userRepository;
    private final ChatRoomRepository chatRoomRepository;
    @Override
    @Transactional
    public ChatRoomSaveResponseDto save(ChatRoomSaveRequestDto chatRoomSaveRequestDto) {
        String publisher = chatRoomSaveRequestDto.getPublisher();
        String subscriber = chatRoomSaveRequestDto.getSubscriber();

        if(publisher == null || subscriber == null) throw new StudentIdNotFound();

        Date createdAt = new Date();

        User pub = userRepository.findByStudentId(publisher)
                .orElseThrow(() -> new AccountNotFound(publisher));

        User sub = userRepository.findByStudentId(subscriber)
                .orElseThrow(() -> new AccountNotFound(subscriber));

        ChatRoom build = ChatRoom.builder()
                .publisher(pub)
                .subscriber(sub)
                .createdAt(createdAt)
                .deleted(false)
                .build();

        chatRoomRepository.save(build);

        return ChatRoomSaveResponseDto.builder()
                .publisher(publisher)
                .subscriber(subscriber)
                .createdAt(createdAt)
                .build();

    }

    @Override
    @Transactional
    public void delete(Long roomId) {
        if(roomId == null) throw new RoomIdNotFound();

        ChatRoom chatRoom = chatRoomRepository.findByRoomId(roomId)
                .orElseThrow(() -> new ChatRoomNotFound(roomId));

        chatRoom.setDeleted(true);
        chatRoomRepository.save(chatRoom);
    }

    @Override
    public List<ChatRoomFindResponseDto> findByStudentId(String studentId) {
        if(studentId == null) throw new StudentIdNotFound();


        List<ChatRoom> chatRooms = chatRoomRepository.findByStudentId(studentId);
        List<ChatRoomFindResponseDto> result = new ArrayList<>();
        for (ChatRoom chatRoom : chatRooms) {
            ChatRoomFindResponseDto build = ChatRoomFindResponseDto.builder()
                    .roomId(chatRoom.getRoomId())
                    .publisher(chatRoom.getPublisher().getStudentId())
                    .subscriber(chatRoom.getSubscriber().getStudentId())
                    .createdAt(chatRoom.getCreatedAt()).build();

            result.add(build);
        }

        return result;
    }


}
