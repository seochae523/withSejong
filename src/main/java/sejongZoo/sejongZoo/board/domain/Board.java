package sejongZoo.sejongZoo.board.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import sejongZoo.sejongZoo.user.domain.User;

import java.util.Date;

@Entity(name = "board")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreationTimestamp
    private Date date;
    @Column(columnDefinition = "TEXT")
    @NotNull
    private String content;
    @Column(columnDefinition = "varchar(50)")
    @NotNull
    private String title;
    @ColumnDefault("false")
    private Boolean deleted;
    @ManyToOne
    @JoinColumn(name="USER_ID")
    private User user;

}
