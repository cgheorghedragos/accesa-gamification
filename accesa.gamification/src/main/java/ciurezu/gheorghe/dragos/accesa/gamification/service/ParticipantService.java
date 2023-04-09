package ciurezu.gheorghe.dragos.accesa.gamification.service;

import ciurezu.gheorghe.dragos.accesa.gamification.data.shared.ParticipantDto;
import ciurezu.gheorghe.dragos.accesa.gamification.data.shared.QuestDto;
import ciurezu.gheorghe.dragos.accesa.gamification.exceptions.BadRequestException;

public interface ParticipantService {
    ParticipantDto addParticipantToQuest(ParticipantDto participantDto, QuestDto questDto, String imgUrl) throws BadRequestException;
}
