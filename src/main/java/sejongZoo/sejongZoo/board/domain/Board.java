package sejongZoo.sejongZoo.board.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import sejongZoo.sejongZoo.board.dto.request.BoardUpdateRequestDto;
import sejongZoo.sejongZoo.user.domain.User;

import java.util.Date;
import java.util.Set;

@Entity(name = "board")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(name="created_at" , nullable = false)
    private Date createdAt;

    @Column(name="content", columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(name="title", columnDefinition = "varchar(50)", nullable = false, length = 50)
    private String title;

    @Column(name="price", columnDefinition = "int", nullable = false)
    private Integer price;

    @Column(name="status", columnDefinition = "integer", nullable = false)
    @ColumnDefault("0")
    private Integer status;


    @Column(name = "deleted", columnDefinition = "tinyint", nullable = false)
    @ColumnDefault("false")
    private Boolean deleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="USER_ID")
    private User user;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="BOARD_ID")
    private Set<Image> image;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="BOARD_ID")
    private Set<Tag> tag;
    public void updateBoard(BoardUpdateRequestDto dto,Set<Image> image, Set<Tag> tag){
        this.content = dto.getContent();
        this.title = dto.getTitle();
        this.price = dto.getPrice();
        this.image = image;
        this.tag = tag;
    }

    public void updateCreatedAt(Date date){
        this.createdAt = date;
    }
    public void setDeleted(Boolean deleted){
        this.deleted = deleted;
    }

    public void setStatus(Integer status){ this.status =status;}
}
