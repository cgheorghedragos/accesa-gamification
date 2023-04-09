package ciurezu.gheorghe.dragos.accesa.gamification.service;

import ciurezu.gheorghe.dragos.accesa.gamification.data.remote_respone.AlexFlipNoteApiResponse;
import ciurezu.gheorghe.dragos.accesa.gamification.data.shared.GamificationUserDto;
import ciurezu.gheorghe.dragos.accesa.gamification.exceptions.BadRequestException;

public interface BirdService {
    String getAnotherBird(GamificationUserDto userDto) throws Exception;
    String getMyBird(GamificationUserDto userDto) throws BadRequestException, Exception;

    String lovedBird(GamificationUserDto userDto) throws Exception;
}
