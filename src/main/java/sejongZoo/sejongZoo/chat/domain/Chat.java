package sejongZoo.sejongZoo.chat.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Getter
@NoArgsConstructor
public class Chat {
//    @Id
//    @Column(name="MESSAGE_ID")
    private Long id;
    private String sender;

    private String senderEmail;

    private String message;

//    @CreatedDate
//    @Column(updatable = false)
    private LocalDateTime sendDate;

    @Builder
    public Chat(String sender, String senderEmail, String message, LocalDateTime sendDate) {
        this.sender = sender;
        this.senderEmail = senderEmail;
        this.message = message;
        this.sendDate = sendDate;
    }


    public static Chat createChat(String sender, String senderEmail, String message) {
        return Chat.builder()
                .sender(sender)
                .senderEmail(senderEmail)
                .message(message)
                .build();
    }
}
