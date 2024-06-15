package sejongZoo.sejongZoo.chat.domain;



import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import sejongZoo.sejongZoo.board.domain.Board;
import sejongZoo.sejongZoo.user.domain.User;

import java.util.Date;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="chat_room")
public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="room_id")
    private Long roomId;


    @Column(name="created_at", nullable = false)
    private Date createdAt;


    @ManyToOne
    @JoinColumn(name = "publisher_id", nullable = false)
    private User publisher;


    @ManyToOne
    @JoinColumn(name = "subscriber_id", nullable = false)
    private User subscriber;


    @ManyToOne
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;

    @Column(name="deleted", nullable = false)
    @ColumnDefault("false")
    private Boolean deleted;

    public void setDeleted(Boolean deleted){
        this.deleted = deleted;
    }
}
