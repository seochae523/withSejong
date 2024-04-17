package sejongZoo.sejongZoo.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sejongZoo.sejongZoo.user.domain.User;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByStudentId(@Param("studentId") String studentId);
    Optional<User> findByNickname(@Param("nickname") String nickname);
}
