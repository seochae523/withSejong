package sejongZoo.sejongZoo.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sejongZoo.sejongZoo.board.domain.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
