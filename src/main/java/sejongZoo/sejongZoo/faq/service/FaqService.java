package sejongZoo.sejongZoo.faq.service;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.util.TableUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sejongZoo.sejongZoo.common.exception.faq.CreatedAtNotFound;
import sejongZoo.sejongZoo.common.exception.faq.FaqContextNotFound;
import sejongZoo.sejongZoo.common.exception.faq.FaqIdNotFound;
import sejongZoo.sejongZoo.common.exception.faq.FaqTitleNotFound;
import sejongZoo.sejongZoo.faq.domain.Faq;
import sejongZoo.sejongZoo.faq.dto.request.FaqSaveRequestDto;
import sejongZoo.sejongZoo.faq.dto.request.FaqUpdateRequestDto;
import sejongZoo.sejongZoo.faq.dto.response.FaqDeleteResponseDto;
import sejongZoo.sejongZoo.faq.dto.response.FaqFindResponseDto;
import sejongZoo.sejongZoo.faq.dto.response.FaqSaveResponseDto;
import sejongZoo.sejongZoo.faq.dto.response.FaqUpdateResponseDto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class FaqService {
    private final DynamoDBMapper dynamoDBMapper;
    private final AmazonDynamoDB amazonDynamoDB;

    private void createFAQTableIfNotExists() {
        CreateTableRequest createTableRequest = dynamoDBMapper.generateCreateTableRequest(Faq.class)
                .withProvisionedThroughput(new ProvisionedThroughput(1L, 1L));
        TableUtils.createTableIfNotExists(amazonDynamoDB, createTableRequest);
    }

    public List<FaqFindResponseDto> findAll(){
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        List<Faq> result = dynamoDBMapper.scan(Faq.class, scanExpression);

        List<FaqFindResponseDto> dtoList = result.stream()
                .map(FaqFindResponseDto::new)
                .collect(Collectors.toList());

        return dtoList;
    }
    public FaqSaveResponseDto saveFaq(FaqSaveRequestDto faqSaveRequestDto) {
        this.createFAQTableIfNotExists();
        String context = faqSaveRequestDto.getContext();
        String title = faqSaveRequestDto.getTitle();

        if(context == null){
            throw new FaqContextNotFound();
        }
        if(title == null){
            throw new FaqTitleNotFound();
        }

        Date createdAt = new Date();
        dynamoDBMapper.save(faqSaveRequestDto.toEntity(createdAt));

        return FaqSaveResponseDto.builder()
                .title(title)
                .context(context)
                .build();
    }

    public FaqUpdateResponseDto updateFaq(FaqUpdateRequestDto faqUpdateRequestDto){
        String context = faqUpdateRequestDto.getContext();
        String title = faqUpdateRequestDto.getTitle();
        String id = faqUpdateRequestDto.getId();
        Date createdAt = faqUpdateRequestDto.getCreatedAt();
        if(id == null){
            throw new FaqIdNotFound();
        }
        if(context == null){
            throw new FaqContextNotFound();
        }
        if(title == null){
            throw new FaqTitleNotFound();
        }

        Faq load = dynamoDBMapper.load(Faq.class, id, createdAt);
        System.out.println("load = " + load);
        load.setContext(context);
        load.setTitle(title);


        dynamoDBMapper.save(load);

        return FaqUpdateResponseDto.builder()
                .title(faqUpdateRequestDto.getTitle())
                .context(faqUpdateRequestDto.getContext())
                .build();
    }

    public FaqDeleteResponseDto deleteFaq(String id, Date createdAt) {
        if(id == null){
            throw new FaqIdNotFound();
        }
        if(createdAt == null){
            throw new CreatedAtNotFound();
        }

        Faq load = dynamoDBMapper.load(Faq.class, id, createdAt);

        dynamoDBMapper.delete(load);

        return FaqDeleteResponseDto.builder()
                .context(load.getContext())
                .title(load.getTitle())
                .build();
    }

}
