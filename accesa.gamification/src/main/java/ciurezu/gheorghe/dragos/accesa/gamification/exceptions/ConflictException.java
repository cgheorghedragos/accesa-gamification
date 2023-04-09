package ciurezu.gheorghe.dragos.accesa.gamification.exceptions;

import lombok.AllArgsConstructor;


public class ConflictException extends Exception{
    public ConflictException(String message){
        super(message);
    }
}
