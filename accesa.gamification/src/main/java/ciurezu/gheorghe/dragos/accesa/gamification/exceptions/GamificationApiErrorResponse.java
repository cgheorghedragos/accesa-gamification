package ciurezu.gheorghe.dragos.accesa.gamification.exceptions;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public record GamificationApiErrorResponse(String message, HttpStatus httpStatus, ZonedDateTime zonedDateTime) {
}
