package sejongZoo.sejongZoo.chat.domain;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter // Setters are used in aws-dynamodb-sdk
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamoDBTable(tableName = "chat_room")
public class ChatRoom {
    @DynamoDBAttribute(attributeName = "room_id")
    @DynamoDBHashKey
    private String roomId;

    @DynamoDBAttribute(attributeName = "room_name")
    private String roomName;
    @DynamoDBAttribute(attributeName = "chat")
    private List<Chat> chat;

    @DynamoDBAttribute(attributeName = "created_at")
    @DynamoDBRangeKey
    private Date createdAt;
}
