package sejongZoo.sejongZoo.faq.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sejongZoo.sejongZoo.faq.dto.request.FaqSaveRequestDto;
import sejongZoo.sejongZoo.faq.dto.request.FaqUpdateRequestDto;
import sejongZoo.sejongZoo.faq.dto.response.FaqDeleteResponseDto;
import sejongZoo.sejongZoo.faq.dto.response.FaqFindResponseDto;
import sejongZoo.sejongZoo.faq.dto.response.FaqSaveResponseDto;
import sejongZoo.sejongZoo.faq.dto.response.FaqUpdateResponseDto;
import sejongZoo.sejongZoo.faq.service.FaqService;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping
@SecurityRequirement(name = "Bearer Authentication")
@Validated
public class FaqController {
    private final FaqService faqService;

    @GetMapping("/user/faq")
    @Operation(summary = "자주 묻는 질문",
            description = "자주 묻는 질문 조회")
    public ResponseEntity<List<FaqFindResponseDto>> findAll(){
        return new ResponseEntity<>(faqService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/admin/faq/save")
    @Operation(summary = "자주 묻는 질문 저장",
            description = "자주 묻는 질문 저장")
    public ResponseEntity<FaqSaveResponseDto> save(@RequestBody @Valid FaqSaveRequestDto faqSaveRequestDto){
        return new ResponseEntity<>(faqService.saveFaq(faqSaveRequestDto), HttpStatus.CREATED);
    }
    @PutMapping("/admin/faq/update")
    @Operation(summary = "자주 묻는 질문 업데이트",
            description = "자주 묻는 질문 업데이트")
    public ResponseEntity<FaqUpdateResponseDto> update(@RequestBody @Valid FaqUpdateRequestDto faqUpdateRequestDto){
        return new ResponseEntity<>(faqService.updateFaq(faqUpdateRequestDto), HttpStatus.OK);
    }
    @DeleteMapping("/admin/faq/delete")
    @Operation(summary = "자주 묻는 질문 삭제",
            description = "자주 묻는 질문 삭제")
    public ResponseEntity<FaqDeleteResponseDto> delete(@NotNull(message = "Faq Id Not Found.")
                                                           @RequestParam(value = "id", required = false) String id,

                                                       @NotNull(message = "Faq Created At Not Found.")
                                                       @RequestParam(value = "createdAt", required = false)
                                                       @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date createdAt) throws ParseException {
        return new ResponseEntity<>(faqService.deleteFaq(id, createdAt), HttpStatus.OK);
    }

}

