package sejongZoo.sejongZoo.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sejongZoo.sejongZoo.chat.domain.ChatRoom;
import sejongZoo.sejongZoo.chat.dto.response.ChatRoomFindResponseDto;
import sejongZoo.sejongZoo.user.domain.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    @Query("select r from ChatRoom r left join fetch r.publisher p left join fetch r.subscriber s left join fetch r.board b " +
            "where (s.studentId=:studentId or p.studentId=:studentId) and r.deleted=false")
    List<ChatRoom> findByStudentId(@Param("studentId") String studentId);

    Optional<ChatRoom> findByRoomId(@Param("roomId") Long roomId);
}
