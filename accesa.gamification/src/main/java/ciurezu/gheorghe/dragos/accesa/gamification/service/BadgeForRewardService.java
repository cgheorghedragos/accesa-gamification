package ciurezu.gheorghe.dragos.accesa.gamification.service;

import ciurezu.gheorghe.dragos.accesa.gamification.data.entity.BadgeForReward;
import ciurezu.gheorghe.dragos.accesa.gamification.data.shared.*;
import ciurezu.gheorghe.dragos.accesa.gamification.data.shared.specific_dto.PrizeWithBadgesDto;
import ciurezu.gheorghe.dragos.accesa.gamification.exceptions.BadRequestException;

import java.util.List;
import java.util.Set;

public interface BadgeForRewardService {
    void transferBadgeFromUserToReward(Set<PrizeDto> prizeDtos, QuestDto questDto);

    void transferBadgeFromRewardToUser(List<PrizeWithBadgesDto> prizeDtos, List<ParticipantDto> participantDtoList,QuestDto questDto) throws BadRequestException;
}
