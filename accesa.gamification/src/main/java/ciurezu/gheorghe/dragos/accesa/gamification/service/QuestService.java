package ciurezu.gheorghe.dragos.accesa.gamification.service;

import ciurezu.gheorghe.dragos.accesa.gamification.data.shared.ParticipantDto;
import ciurezu.gheorghe.dragos.accesa.gamification.data.shared.PrizeDto;
import ciurezu.gheorghe.dragos.accesa.gamification.data.shared.QuestCategoryDto;
import ciurezu.gheorghe.dragos.accesa.gamification.data.shared.QuestDto;
import ciurezu.gheorghe.dragos.accesa.gamification.data.shared.specific_dto.PrizeWithBadgesDto;
import ciurezu.gheorghe.dragos.accesa.gamification.data.shared.specific_dto.QuestWithCategoryDto;
import ciurezu.gheorghe.dragos.accesa.gamification.exceptions.BadRequestException;
import ciurezu.gheorghe.dragos.accesa.gamification.exceptions.ConflictException;

import java.util.List;

public interface QuestService {
    QuestCategoryDto saveCategory(QuestCategoryDto questCategoryDto) throws ConflictException;
    QuestDto saveQuest(QuestDto questDto) throws Exception;

    List<QuestWithCategoryDto> getAllActiveQuests();

    List<ParticipantDto> getAllParticipants(QuestDto questDto) throws BadRequestException;

    List<PrizeWithBadgesDto> getAllPrizes(QuestDto questDto) throws BadRequestException;
}
