package sejongZoo.sejongZoo.chat.domain;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter // Setters are used in aws-dynamodb-sdk
@Builder
public class Chat {
    private String sender;
    private String message;
    private LocalDateTime sendDate;

    public static Chat createChat(String sender, String message) {
        return Chat.builder()
                .sender(sender)
                .message(message)
                .build();
    }
}
