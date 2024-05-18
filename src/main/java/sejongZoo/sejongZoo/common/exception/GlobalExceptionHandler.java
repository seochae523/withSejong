package sejongZoo.sejongZoo.common.exception;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sejongZoo.sejongZoo.common.exception.board.*;
import sejongZoo.sejongZoo.common.exception.chat.ChatRoomNotFound;
import sejongZoo.sejongZoo.common.exception.chat.MessageNotFound;
import sejongZoo.sejongZoo.common.exception.chat.RoomIdNotFound;
import sejongZoo.sejongZoo.common.exception.chat.SenderNotFound;
import sejongZoo.sejongZoo.common.exception.chat.ChatCreatedAtNotFound;
import sejongZoo.sejongZoo.common.exception.faq.*;
import sejongZoo.sejongZoo.common.exception.mail.EmailContentNotFound;
import sejongZoo.sejongZoo.common.exception.mail.EmailTitleNotFound;
import sejongZoo.sejongZoo.common.exception.token.*;
import sejongZoo.sejongZoo.common.exception.user.*;


@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    /**
     * SET -> Sejong Error Token
     */
    @ExceptionHandler(InvalidJwtToken.class)
    public ResponseEntity<ApiErrorResponse> handleException(InvalidJwtToken ex){
        ApiErrorResponse response = new ApiErrorResponse("SET-001", "Invalid JWT Token. Token : " + ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(TokenExpired.class)
    public ResponseEntity<ApiErrorResponse> handleException(TokenExpired ex){
        ApiErrorResponse response = new ApiErrorResponse("SET-002", "Token Has Been Expired. Token : " + ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(InvalidRefreshToken.class)
    public ResponseEntity<ApiErrorResponse> handleException(InvalidRefreshToken ex){
        ApiErrorResponse response = new ApiErrorResponse("SET-003", "Invalid Refresh Token. Token : "+ ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(RefreshTokenNotFound.class)
    public ResponseEntity<ApiErrorResponse> handleException(RefreshTokenNotFound ex){
        ApiErrorResponse response = new ApiErrorResponse("SET-004", "Refresh Token Not Found : " + ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(IncorrectRefreshToken.class)
    public ResponseEntity<ApiErrorResponse> handleException(IncorrectRefreshToken ex){
        ApiErrorResponse response = new ApiErrorResponse("SET-005", "Incorrect Refresh Token. Token : " + ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    /**
     * SEU -> Sejong Error User
     */
    @ExceptionHandler(AccountNotFound.class)
    public ResponseEntity<ApiErrorResponse> handleException(AccountNotFound ex){
        ApiErrorResponse response = new ApiErrorResponse("SEU-001", "Account Not Found. Student ID : " + ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(StudentIdNotFound.class)
    public ResponseEntity<ApiErrorResponse> handleException(StudentIdNotFound ex){
        ApiErrorResponse response = new ApiErrorResponse("SEU-002", "Student Id Not Found.");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserIdNotFound.class)
    public ResponseEntity<ApiErrorResponse> handleException(UserIdNotFound ex){
        ApiErrorResponse response = new ApiErrorResponse("SEU-003", "User Id (Primary key) Not Found.");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MajorNotFound.class)
    public ResponseEntity<ApiErrorResponse> handleException(MajorNotFound ex){
        ApiErrorResponse response = new ApiErrorResponse("SEU-004", "Major Not Found.");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(NicknameNotFound.class)
    public ResponseEntity<ApiErrorResponse> handleException(NicknameNotFound ex){
        ApiErrorResponse response = new ApiErrorResponse("SEU-005", "Nickname Not Found.");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(PasswordNotFound.class)
    public ResponseEntity<ApiErrorResponse> handleException(PasswordNotFound ex){
        ApiErrorResponse response = new ApiErrorResponse("SEU-006", "Password Not Found.");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(UserNameNotFound.class)
    public ResponseEntity<ApiErrorResponse> handleException(UserNameNotFound ex){
        ApiErrorResponse response = new ApiErrorResponse("SEU-007", "User Name Not Found.");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(DuplicatedNickname.class)
    public ResponseEntity<ApiErrorResponse> handleException(DuplicatedNickname ex){
        ApiErrorResponse response = new ApiErrorResponse("SEU-008", "Duplicated Nickname. Nickname : " + ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(DuplicatedStudentId.class)
    public ResponseEntity<ApiErrorResponse> handleException(DuplicatedStudentId ex){
        ApiErrorResponse response = new ApiErrorResponse("SEU-008", "Duplicated Student Id. Student Id : " + ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }
    @ExceptionHandler(IsDeletedNotFound.class)
    public ResponseEntity<ApiErrorResponse> handleException(IsDeletedNotFound ex){
        ApiErrorResponse response = new ApiErrorResponse("SEU-009", "Is Deleted Not Found.");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(IsSignedNotFound.class)
    public ResponseEntity<ApiErrorResponse> handleException(IsSignedNotFound ex){
        ApiErrorResponse response = new ApiErrorResponse("SEU-010", "Is Signed Not Found.");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AccountAlreadyExist.class)
    public ResponseEntity<ApiErrorResponse> handleException(AccountAlreadyExist ex){
        ApiErrorResponse response = new ApiErrorResponse("SEU-011", "Account Already Exist. Student Id : " + ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }
    @ExceptionHandler(EmailContentNotFound.class)
    public ResponseEntity<ApiErrorResponse> handleException(EmailContentNotFound ex){
        ApiErrorResponse response = new ApiErrorResponse("SEU-012", "Email Content Not Found");
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }
    @ExceptionHandler(EmailTitleNotFound.class)
    public ResponseEntity<ApiErrorResponse> handleException(EmailTitleNotFound ex){
        ApiErrorResponse response = new ApiErrorResponse("SEU-013", "Email Title Not Found");
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InvalidAccount.class)
    public ResponseEntity<ApiErrorResponse> handleException(InvalidAccount ex){
        ApiErrorResponse response = new ApiErrorResponse("SEU-014", "Invalid Account. Student Id : " + ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    /**
     * SEB -> Sejong Error Board
     */

    @ExceptionHandler(ContentNotFound.class)
    public ResponseEntity<ApiErrorResponse> handleException(ContentNotFound ex){
        ApiErrorResponse response = new ApiErrorResponse("SEB-001", "Content Not Found.");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(TitleNotFound.class)
    public ResponseEntity<ApiErrorResponse> handleException(TitleNotFound ex){
        ApiErrorResponse response = new ApiErrorResponse("SEB-002", "Title Not Found.");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(PriceNotFound.class)
    public ResponseEntity<ApiErrorResponse> handleException(PriceNotFound ex){
        ApiErrorResponse response = new ApiErrorResponse("SEB-003", "Price Not Found.");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BoardNotFound.class)
    public ResponseEntity<ApiErrorResponse> handleException(BoardNotFound ex){
        ApiErrorResponse response = new ApiErrorResponse("SEB-004", "Board Not Found. Board id : "+ ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(BoardIdNotFound.class)
    public ResponseEntity<ApiErrorResponse> handleException(BoardIdNotFound ex){
        ApiErrorResponse response = new ApiErrorResponse("SEB-005", "Board Id Not Found.");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(KeywordNotFound.class)
    public ResponseEntity<ApiErrorResponse> handleException(KeywordNotFound ex){
        ApiErrorResponse response = new ApiErrorResponse("SEB-006", "Keyword Not Found.");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ImageNotFound.class)
    public ResponseEntity<ApiErrorResponse> handleException(ImageNotFound ex){
        ApiErrorResponse response = new ApiErrorResponse("SEB-007", "Image Not Found. Id : "+ ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ImageIdNotFound.class)
    public ResponseEntity<ApiErrorResponse> handleException(ImageIdNotFound ex){
        ApiErrorResponse response = new ApiErrorResponse("SEB-008", "Image Id Not Found. ");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ImageUrlNotFound.class)
    public ResponseEntity<ApiErrorResponse> handleException(ImageUrlNotFound ex){
        ApiErrorResponse response = new ApiErrorResponse("SEB-009", "Image Url Not Found. ");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(TagNotFound.class)
    public ResponseEntity<ApiErrorResponse> handleException(TagNotFound ex){
        ApiErrorResponse response = new ApiErrorResponse("SEB-010", "Tag Not Found. Tag Id : " + ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    /**
     * SEF -> Sejong Error Faq
     */

    @ExceptionHandler(FaqContextNotFound.class)
    public ResponseEntity<ApiErrorResponse> handleException(FaqContextNotFound ex){
        ApiErrorResponse response = new ApiErrorResponse("SEF-001", "Faq Context Not Found.");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FaqIdNotFound.class)
    public ResponseEntity<ApiErrorResponse> handleException(FaqIdNotFound ex){
        ApiErrorResponse response = new ApiErrorResponse("SEF-002", "Faq Id Not Found.");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FaqTitleNotFound.class)
    public ResponseEntity<ApiErrorResponse> handleException(FaqTitleNotFound ex){
        ApiErrorResponse response = new ApiErrorResponse("SEF-003", "Faq Title Not Found.");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FaqNotFound.class)
    public ResponseEntity<ApiErrorResponse> handleException(FaqNotFound ex){
        ApiErrorResponse response = new ApiErrorResponse("SEF-004", "Faq Not Found. Id : " + ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FaqCreatedAtNotFound.class)
    public ResponseEntity<ApiErrorResponse> handleException(FaqCreatedAtNotFound ex){
        ApiErrorResponse response = new ApiErrorResponse("SEF-005", "Faq Created At Not Found.");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    /**
     * SEC -> Sejong Error Chatting
     */
    @ExceptionHandler(ChatRoomNotFound.class)
    public ResponseEntity<ApiErrorResponse> handleException(ChatRoomNotFound ex){
        ApiErrorResponse response = new ApiErrorResponse("SEC-001", "Chat Room Not Found. Room Id : " + ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(RoomIdNotFound.class)
    public ResponseEntity<ApiErrorResponse> handleException(RoomIdNotFound ex){
        ApiErrorResponse response = new ApiErrorResponse("SEC-002", "Room Id Not Found.");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ChatCreatedAtNotFound.class)
    public ResponseEntity<ApiErrorResponse> handleException(ChatCreatedAtNotFound ex){
        ApiErrorResponse response = new ApiErrorResponse("SEC-003", "Chat Created At Not Found.");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(SenderNotFound.class)
    public ResponseEntity<ApiErrorResponse> handleException(SenderNotFound ex){
        ApiErrorResponse response = new ApiErrorResponse("SEC-004", "Sender Not Found.");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(MessageNotFound.class)
    public ResponseEntity<ApiErrorResponse> handleException(MessageNotFound ex){
        ApiErrorResponse response = new ApiErrorResponse("SEC-005", "Message Not Found.");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}

