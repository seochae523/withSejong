package sejongZoo.sejongZoo.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sejongZoo.sejongZoo.board.domain.Board;

import java.util.List;


@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

    @Query("SELECT b from board b left join fetch b.user u left join fetch b.image i left join fetch b.tag t order by b.createdAt DESC ")
    Page<Board> findAllWithUserAndImage(Pageable pageable);


    @Query("select b from board b join fetch b.user u left join fetch b.image i left join fetch b.tag t " +
            "where b.title like concat(:keyword, '%') or b.content like concat(:keyword, '%')" +
            "or b.id=(select t2.board.id from tag t2 where t2.category like concat(:keyword, '%')) " +
            "order by b.createdAt desc")
    Page<Board> searchWithKeyword(@Param("keyword") String keyword, Pageable pageable);

    @Query("select b from board b join fetch b.user u left join fetch b.image i left join fetch b.tag t" +
            " where u.studentId=:studentId")
    Page<Board> findByStudentId(@Param("studentId") String studentId, Pageable pageable);


    @Query("select b from board b left join tag t where t.category in :tags")
    Page<Board> findByTag(@Param("tags")List<String> tags, Pageable pageable);
}
