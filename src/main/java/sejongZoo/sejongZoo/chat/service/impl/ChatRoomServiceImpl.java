package sejongZoo.sejongZoo.chat.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sejongZoo.sejongZoo.board.domain.Board;
import sejongZoo.sejongZoo.board.repository.BoardRepository;
import sejongZoo.sejongZoo.chat.domain.ChatRoom;
import sejongZoo.sejongZoo.chat.dto.request.ChatRoomSaveRequestDto;
import sejongZoo.sejongZoo.chat.dto.response.ChatRoomFindResponseDto;
import sejongZoo.sejongZoo.chat.dto.response.ChatRoomSaveResponseDto;
import sejongZoo.sejongZoo.chat.repository.ChatRoomRepository;
import sejongZoo.sejongZoo.chat.service.ChatRoomService;
import sejongZoo.sejongZoo.common.exception.board.BoardNotFound;
import sejongZoo.sejongZoo.common.exception.chat.ChatRoomNotFound;
import sejongZoo.sejongZoo.common.exception.user.AccountNotFound;
import sejongZoo.sejongZoo.user.domain.User;
import sejongZoo.sejongZoo.user.repository.UserRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class ChatRoomServiceImpl implements ChatRoomService {
    private final UserRepository userRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final BoardRepository boardRepository;
    @Override
    @Transactional
    public ChatRoomSaveResponseDto save(ChatRoomSaveRequestDto chatRoomSaveRequestDto) {
        String publisher = chatRoomSaveRequestDto.getPublisher();
        String subscriber = chatRoomSaveRequestDto.getSubscriber();
        Long boardId = chatRoomSaveRequestDto.getBoardId();

        Date createdAt = new Date();

        User pub = userRepository.findByStudentId(publisher)
                .orElseThrow(() -> new AccountNotFound(publisher));

        User sub = userRepository.findByStudentId(subscriber)
                .orElseThrow(() -> new AccountNotFound(subscriber));

        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new BoardNotFound(boardId));

        Optional<ChatRoom> chatRoom = chatRoomRepository.findByBoardAndPublisherAndSubscriber(board.getId(), pub.getStudentId(), sub.getStudentId());

        if(chatRoom.isPresent()){
            return ChatRoomSaveResponseDto.builder()
                    .id(chatRoom.get().getRoomId())
                    .boardTitle(board.getTitle())
                    .publisher(publisher)
                    .subscriber(subscriber)
                    .createdAt(createdAt)
                    .isCreated(true)
                    .build();
        }

        ChatRoom build = ChatRoom.builder()
                .publisher(pub)
                .subscriber(sub)
                .createdAt(createdAt)
                .board(board)
                .deleted(false)
                .build();

        ChatRoom save = chatRoomRepository.save(build);

        return ChatRoomSaveResponseDto.builder()
                .id(save.getRoomId())
                .boardTitle(board.getTitle())
                .publisher(publisher)
                .subscriber(subscriber)
                .createdAt(createdAt)
                .isCreated(false)
                .build();

    }

    @Override
    @Transactional
    public void delete(Long roomId) {
        ChatRoom chatRoom = chatRoomRepository.findByRoomId(roomId)
                .orElseThrow(() -> new ChatRoomNotFound(roomId));

        chatRoom.setDeleted(true);
        chatRoomRepository.save(chatRoom);
    }

    @Override
    public List<ChatRoomFindResponseDto> findByStudentId(String studentId) {

        List<ChatRoom> chatRooms = chatRoomRepository.findByStudentId(studentId);
        List<ChatRoomFindResponseDto> result = new ArrayList<>();
        for (ChatRoom chatRoom : chatRooms) {
            ChatRoomFindResponseDto build = ChatRoomFindResponseDto.builder()
                    .boardTitle(chatRoom.getBoard().getTitle())
                    .roomId(chatRoom.getRoomId())
                    .publisher(chatRoom.getPublisher().getStudentId())
                    .subscriber(chatRoom.getSubscriber().getStudentId())
                    .createdAt(chatRoom.getCreatedAt()).build();

            result.add(build);
        }

        return result;
    }


}
