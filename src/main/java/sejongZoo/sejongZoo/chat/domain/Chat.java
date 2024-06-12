package sejongZoo.sejongZoo.chat.domain;


import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.*;


import java.util.Date;


@Getter
@Setter // Setters are used in aws-dynamodb-sdk
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamoDBTable(tableName = "chat")
public class Chat {
    @DynamoDBHashKey(attributeName = "id")
    private Long id;

    @DynamoDBAttribute
    private String sender;

    @DynamoDBAttribute
    private String message;

    @DynamoDBAttribute
    @DynamoDBRangeKey(attributeName = "created_at")
    private Date createdAt;

}
