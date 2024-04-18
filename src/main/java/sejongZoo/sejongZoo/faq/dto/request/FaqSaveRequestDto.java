package sejongZoo.sejongZoo.faq.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import sejongZoo.sejongZoo.faq.domain.Faq;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class FaqSaveRequestDto {
    private String title;
    private String context;



    public Faq toEntity(Date createdAt){
        return Faq.builder()
                .title(title)
                .context(context)
                .createdAt(createdAt)
                .build();
    }
}
