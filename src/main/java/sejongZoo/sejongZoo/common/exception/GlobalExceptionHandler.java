package sejongZoo.sejongZoo.common.exception;

import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sejongZoo.sejongZoo.board.domain.Tag;
import sejongZoo.sejongZoo.common.exception.board.*;
import sejongZoo.sejongZoo.common.exception.chat.ChatNotFound;
import sejongZoo.sejongZoo.common.exception.chat.ChatRoomNotFound;
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
     * SEC -> Sejong Error Chat
     */

    @ExceptionHandler(ChatRoomNotFound.class)
    public ResponseEntity<ApiErrorResponse> handleException(ChatRoomNotFound ex){
        ApiErrorResponse response = new ApiErrorResponse("SEC-001", "Chat Room Not Found. Room Id : " + ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ChatNotFound.class)
    public ResponseEntity<ApiErrorResponse> handleException(ChatNotFound ex){
        ApiErrorResponse response = new ApiErrorResponse("SEC-002", "Chat Not Found. Room Id : " + ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    /**
     * SEU -> Sejong Error User
     */
    @ExceptionHandler(AccountNotFound.class)
    public ResponseEntity<ApiErrorResponse> handleException(AccountNotFound ex){
        ApiErrorResponse response = new ApiErrorResponse("SEU-001", "Account Not Found. Student Id : " + ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(DuplicatedNickname.class)
    public ResponseEntity<ApiErrorResponse> handleException(DuplicatedNickname ex){
        ApiErrorResponse response = new ApiErrorResponse("SEU-002", "Duplicated Nickname. Nickname : " + ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(DuplicatedStudentId.class)
    public ResponseEntity<ApiErrorResponse> handleException(DuplicatedStudentId ex){
        ApiErrorResponse response = new ApiErrorResponse("SEU-003", "Duplicated Student Id. Student Id : " + ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(AccountAlreadyExist.class)
    public ResponseEntity<ApiErrorResponse> handleException(AccountAlreadyExist ex){
        ApiErrorResponse response = new ApiErrorResponse("SEU-001", "Account Already Exist. Student Id : " + ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }
    @ExceptionHandler(InvalidAccount.class)
    public ResponseEntity<ApiErrorResponse> handleException(InvalidAccount ex){
        ApiErrorResponse response = new ApiErrorResponse("SEU-002", "Invalid Account. Student Id : " + ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    /**
     * SEB -> Sejong Error Board
     */


    @ExceptionHandler(BoardNotFound.class)
    public ResponseEntity<ApiErrorResponse> handleException(BoardNotFound ex){
        ApiErrorResponse response = new ApiErrorResponse("SEB-001", "Board Not Found. Id : "+ ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ImageNotFound.class)
    public ResponseEntity<ApiErrorResponse> handleException(ImageNotFound ex){
        ApiErrorResponse response = new ApiErrorResponse("SEB-002", "Image Not Found. Id : "+ ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(TagNotFound.class)
    public ResponseEntity<ApiErrorResponse> handleException(TagNotFound ex){
        ApiErrorResponse response = new ApiErrorResponse("SEB-003", "Tag Not Found. Id : "+ ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    /**
     * SEP -> Sejong Error Parameter
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ApiErrorResponse> handleValidationException(MethodArgumentNotValidException e) {
        ApiErrorResponse response = new ApiErrorResponse("SEP-001", e.getFieldError().getDefaultMessage());
        log.error("Error = {}", e.getFieldError().getDefaultMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    ResponseEntity<ApiErrorResponse> handleValidationException(ConstraintViolationException e) {
        ApiErrorResponse response = new ApiErrorResponse("SEP-002", e.getMessage());
        log.error("Error = {}", e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(MissingServletRequestParameterException.class)
    ResponseEntity<ApiErrorResponse> handleValidationException(MissingServletRequestParameterException e) {
        ApiErrorResponse response = new ApiErrorResponse("SEP-003", e.getMessage());
        log.error("Error = {}", e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}

