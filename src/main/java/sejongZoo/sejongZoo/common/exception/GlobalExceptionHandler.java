package sejongZoo.sejongZoo.common.exception;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sejongZoo.sejongZoo.common.exception.board.*;
import sejongZoo.sejongZoo.common.exception.token.*;
import sejongZoo.sejongZoo.common.exception.user.AccountNotFound;
import sejongZoo.sejongZoo.common.exception.user.StudentIdNotFound;
import sejongZoo.sejongZoo.common.exception.user.UserIdNotFound;


@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    /**
     * SET -> Sejong Error Token
     */
    @ExceptionHandler(InvalidJwtToken.class)
    public ResponseEntity<ApiErrorResponse> invalidJwtToken(InvalidJwtToken ex){
        ApiErrorResponse response = new ApiErrorResponse("SET-001", "Invalid JWT Token. Token : " + ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(TokenExpired.class)
    public ResponseEntity<ApiErrorResponse> tokenExpired(TokenExpired ex){
        ApiErrorResponse response = new ApiErrorResponse("SET-002", "Token Has Been Expired. Token : " + ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(InvalidRefreshToken.class)
    public ResponseEntity<ApiErrorResponse> invalidRefreshToken(InvalidRefreshToken ex){
        ApiErrorResponse response = new ApiErrorResponse("SET-003", "Invalid Refresh Token. Token : "+ ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(RefreshTokenNotFound.class)
    public ResponseEntity<ApiErrorResponse> refreshTokenNotFound(RefreshTokenNotFound ex){
        ApiErrorResponse response = new ApiErrorResponse("SET-004", "Refresh Token Not Found : " + ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(IncorrectRefreshToken.class)
    public ResponseEntity<ApiErrorResponse> incorrectRefreshToken(IncorrectRefreshToken ex){
        ApiErrorResponse response = new ApiErrorResponse("SET-005", "Incorrect Refresh Token. Token : " + ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    /**
     * SEU -> Sejong Error User
     */
    @ExceptionHandler(AccountNotFound.class)
    public ResponseEntity<ApiErrorResponse> accountNotFound(AccountNotFound ex){
        ApiErrorResponse response = new ApiErrorResponse("SEU-001", "Account Not Found. Student ID : " + ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(StudentIdNotFound.class)
    public ResponseEntity<ApiErrorResponse> emailNotFound(StudentIdNotFound ex){
        ApiErrorResponse response = new ApiErrorResponse("SEU-002", "Student Id Not Found.");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserIdNotFound.class)
    public ResponseEntity<ApiErrorResponse> emailNotFound(UserIdNotFound ex){
        ApiErrorResponse response = new ApiErrorResponse("SEU-003", "User Id (Primary key) Not Found.");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    /**
     * SEB -> Sejong Error Board
     */

    @ExceptionHandler(ContentNotFound.class)
    public ResponseEntity<ApiErrorResponse> emailNotFound(ContentNotFound ex){
        ApiErrorResponse response = new ApiErrorResponse("SEB-001", "Content Not Found.");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(TitleNotFound.class)
    public ResponseEntity<ApiErrorResponse> emailNotFound(TitleNotFound ex){
        ApiErrorResponse response = new ApiErrorResponse("SEB-002", "Title Not Found.");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(PriceNotFound.class)
    public ResponseEntity<ApiErrorResponse> emailNotFound(PriceNotFound ex){
        ApiErrorResponse response = new ApiErrorResponse("SEB-003", "Price Not Found.");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BoardNotFound.class)
    public ResponseEntity<ApiErrorResponse> emailNotFound(BoardNotFound ex){
        ApiErrorResponse response = new ApiErrorResponse("SEB-004", "Board Not Found. Board id : "+ ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(BoardIdNotFound.class)
    public ResponseEntity<ApiErrorResponse> emailNotFound(BoardIdNotFound ex){
        ApiErrorResponse response = new ApiErrorResponse("SEB-005", "Board Id Not Found.");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(KeywordNotFound.class)
    public ResponseEntity<ApiErrorResponse> emailNotFound(KeywordNotFound ex){
        ApiErrorResponse response = new ApiErrorResponse("SEB-006", "Keyword Not Found.");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}

