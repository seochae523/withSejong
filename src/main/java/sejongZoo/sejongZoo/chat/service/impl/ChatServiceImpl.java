package sejongZoo.sejongZoo.chat.service.impl;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.util.TableUtils;
import com.google.common.collect.ImmutableMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import sejongZoo.sejongZoo.chat.domain.Chat;

import sejongZoo.sejongZoo.chat.dto.KafkaChatDto;
import sejongZoo.sejongZoo.chat.dto.request.ChatSaveRequestDto;
import sejongZoo.sejongZoo.chat.dto.response.ChatFindResponseDto;
import sejongZoo.sejongZoo.chat.dto.response.ChatSaveResponseDto;
import sejongZoo.sejongZoo.chat.service.ChatService;
import sejongZoo.sejongZoo.common.exception.chat.ChatNotFound;
import sejongZoo.sejongZoo.common.util.KafkaConst;


import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatServiceImpl implements ChatService{
    private final DynamoDBMapper dynamoDBMapper;
    private final AmazonDynamoDB amazonDynamoDB;
    private final KafkaTemplate<String, KafkaChatDto> kafkaTemplate;
    private final SimpMessagingTemplate template;

    private void createChatTableIfNotExists() {
        CreateTableRequest createTableRequest = dynamoDBMapper.generateCreateTableRequest(Chat.class)
                .withProvisionedThroughput(new ProvisionedThroughput(1L, 1L));
        TableUtils.createTableIfNotExists(amazonDynamoDB, createTableRequest);
    }
    @Override
    public ChatSaveResponseDto chat(ChatSaveRequestDto chatSaveRequestDto) {
        String message = chatSaveRequestDto.getMessage();
        Long roomId = chatSaveRequestDto.getRoomId();
        String sender = chatSaveRequestDto.getSender();
        Date createdAt = new Date();

        KafkaChatDto kafkaChatDto = new KafkaChatDto(chatSaveRequestDto, createdAt);
        this.createChatTableIfNotExists();
        this.send(KafkaConst.TOPIC, kafkaChatDto);
        this.saveChat(chatSaveRequestDto);

        return ChatSaveResponseDto.builder()
                .roomId(roomId)
                .createdAt(createdAt)
                .message(message)
                .sender(sender)
                .build();
    }

    @Override
    public List<ChatFindResponseDto> findAll(Long roomId) {
        DynamoDBQueryExpression<Chat> objectDynamoDBQueryExpression = new DynamoDBQueryExpression<Chat>()
                .withKeyConditionExpression("id = :id")
                .withExpressionAttributeValues(ImmutableMap.of(":id", new AttributeValue().withN(roomId.toString())));
        PaginatedQueryList<Chat> query = dynamoDBMapper.query(Chat.class, objectDynamoDBQueryExpression);

        return query.stream()
                .map(ChatFindResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public ChatFindResponseDto findLastChat(Long roomId) {
        DynamoDBQueryExpression<Chat> objectDynamoDBQueryExpression = new DynamoDBQueryExpression<Chat>()
                .withKeyConditionExpression("id = :id")
                .withExpressionAttributeValues(ImmutableMap.of(":id", new AttributeValue().withN(roomId.toString())))
                .withLimit(1)
                .withScanIndexForward(false);


        PaginatedQueryList<Chat> result = dynamoDBMapper.query(Chat.class, objectDynamoDBQueryExpression);

        if(result.isEmpty()) return new ChatFindResponseDto();

        return new ChatFindResponseDto(result.get(0));

    }


    public void send(String topic, KafkaChatDto kafkaChatDto) {
        kafkaTemplate.send(topic, kafkaChatDto);
    }

    @KafkaListener(topics = KafkaConst.TOPIC, groupId = "${spring.kafka.consumer.group-id}", containerFactory = "kafkaChatContainerFactory")
    public void consume(KafkaChatDto kafkaChatDto) {
        template.convertAndSend("/sub/" + kafkaChatDto.getRoomId(), kafkaChatDto);
    }

    @Async
    public void saveChat(ChatSaveRequestDto chatSaveRequestDto){
        String message = chatSaveRequestDto.getMessage();
        Long roomId = chatSaveRequestDto.getRoomId();
        String sender = chatSaveRequestDto.getSender();
        Date createdAt = new Date();

        Chat chat = Chat.builder()
                .id(roomId)
                .message(message)
                .sender(sender)
                .createdAt(createdAt)
                .build();

        dynamoDBMapper.save(chat);
    }
}
