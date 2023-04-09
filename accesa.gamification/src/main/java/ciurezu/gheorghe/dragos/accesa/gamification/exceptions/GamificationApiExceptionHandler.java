package ciurezu.gheorghe.dragos.accesa.gamification.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class GamificationApiExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<?> handleAlreadyExistException(ConflictException e) {
        HttpStatus alreadyExists = HttpStatus.CONFLICT;

        GamificationApiErrorResponse apiErrorResponse = new GamificationApiErrorResponse(
                e.getMessage(),
                alreadyExists,
                ZonedDateTime.now(ZoneId.of("Z")));

        return new ResponseEntity<>(apiErrorResponse, alreadyExists);
    }

    @ExceptionHandler
    public ResponseEntity<?> handleBadRequestException(BadRequestException e){
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        GamificationApiErrorResponse apiErrorResponse = new GamificationApiErrorResponse(
                e.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z")));

        return new ResponseEntity<>(apiErrorResponse, badRequest);
    }
}
