//package sejongZoo.sejongZoo.board.domain;
//
//import jakarta.persistence.*;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import sejongZoo.sejongZoo.user.domain.User;
//
//import java.util.Date;
//
//@Entity
//@Getter
//@NoArgsConstructor
//public class Board {
//    @Id
//    @Column(name = "BOARD_ID")
//    private Long id;
//    private Date date;
//    private String content;
//    private String title;
//    private Boolean deleted;
//
//    @ManyToOne
//    @JoinColumn(name="USER_ID")
//    private User user;
//
//
//    @Builder
//    public Board(Long id, Date date, String content, String title, Boolean deleted, User user) {
//        this.id = id;
//        this.date = date;
//        this.content = content;
//        this.title = title;
//        this.deleted = deleted;
//        this.user = user;
//    }
//}
