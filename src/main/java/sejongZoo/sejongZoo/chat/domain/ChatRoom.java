package sejongZoo.sejongZoo.chat.domain;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import sejongZoo.sejongZoo.board.domain.Board;
import sejongZoo.sejongZoo.user.domain.User;

import java.util.Date;
import java.util.List;

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

    @NotNull
    @Column(name="created_at")
    private Date createdAt;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private User publisher;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "subscriber_id")
    private User subscriber;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @NotNull
    @ColumnDefault("false")
    private Boolean deleted;

    public void setDeleted(Boolean deleted){
        this.deleted = deleted;
    }
}
