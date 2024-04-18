package sejongZoo.sejongZoo.faq.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import sejongZoo.sejongZoo.faq.domain.Faq;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class FaqUpdateRequestDto {
    private String id;
    private String title;
    private String context;
    private Date createdAt;
    public Faq toEntity(Date createdAt){
        return Faq.builder()
                .id(id)
                .title(title)
                .context(context)
                .createdAt(createdAt)
                .build();
    }
}
