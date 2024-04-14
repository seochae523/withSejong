//package sejongZoo.sejongZoo.chat.service.impl;
//
//import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
//import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
//import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
//import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
//import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
//import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
//import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
//import com.amazonaws.services.dynamodbv2.util.TableUtils;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//import sejongZoo.sejongZoo.chat.domain.ChatRoom;
//import sejongZoo.sejongZoo.chat.dto.response.ChatRoomResponseDto;
//import sejongZoo.sejongZoo.chat.service.ChatRoomService;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.UUID;
//import java.util.stream.Collectors;
//
//@Service
//@RequiredArgsConstructor
//@Slf4j
//public class ChatRoomServiceImpl implements ChatRoomService {
//    private final DynamoDBMapper dynamoDBMapper;
//    private final AmazonDynamoDB amazonDynamoDB;
//
//    private void createChatRoomTableIfNotExists() {
//        CreateTableRequest createTableRequest = dynamoDBMapper.generateCreateTableRequest(ChatRoom.class)
//                .withProvisionedThroughput(new ProvisionedThroughput(1L, 1L));
//        TableUtils.createTableIfNotExists(amazonDynamoDB, createTableRequest);
//    }
//
//
//    @Override
//    public ChatRoomResponseDto createRoom(String roomName) {
//        this.createChatRoomTableIfNotExists();
//        String roomId = UUID.randomUUID().toString();
//        ChatRoom chatRoom = ChatRoom.builder()
//                .roomName(roomName)
//                .roomId(roomId)
//                .createdAt(new Date())
//                .chat(new ArrayList<>())
//                .build();
//
//        dynamoDBMapper.save(chatRoom);
//
//        return ChatRoomResponseDto.builder()
//                .name(roomName)
//                .roomId(roomId)
//                .build();
//    }
//
//    @Override
//    public ChatRoomResponseDto removeRoom(String roomId) {
//        return null;
//    }
//
//    @Override
//    public ChatRoomResponseDto findByRoomId(String roomId) {
//        ChatRoom target = ChatRoom.builder().roomId(roomId).build();
//
//        DynamoDBQueryExpression<ChatRoom> queryExpression = new DynamoDBQueryExpression().withHashKeyValues(target);
//        List<ChatRoom> result = dynamoDBMapper.query(ChatRoom.class, queryExpression);
//
//        if(result.size()==0){
//            // exception
//        }
//        ChatRoom chatRoom = result.get(0);
//
//        return ChatRoomResponseDto.builder()
//                .roomId(chatRoom.getRoomId())
//                .name(chatRoom.getRoomName())
//                .build();
//    }
//
//    @Override
//    public List<ChatRoomResponseDto> findAll(){
//        DynamoDBScanExpression dynamoDBScanExpression = new DynamoDBScanExpression();
//
//        List<ChatRoom> load = dynamoDBMapper.scan(ChatRoom.class, dynamoDBScanExpression);
//
//        return load.stream()
//                .map(ChatRoomResponseDto::new)
//                .collect(Collectors.toList());
//    }
//}
