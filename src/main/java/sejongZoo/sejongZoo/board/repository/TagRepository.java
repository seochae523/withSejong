package sejongZoo.sejongZoo.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sejongZoo.sejongZoo.board.domain.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
}
