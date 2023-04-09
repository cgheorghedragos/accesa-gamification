package ciurezu.gheorghe.dragos.accesa.gamification.service;

import ciurezu.gheorghe.dragos.accesa.gamification.data.shared.GamificationUserDto;
import ciurezu.gheorghe.dragos.accesa.gamification.data.shared.PrizeDto;
import ciurezu.gheorghe.dragos.accesa.gamification.data.shared.QuestDto;

import java.util.List;
import java.util.Set;

public interface PrizeService {
    PrizeDto addPrize(PrizeDto prizeDto);
    Set<PrizeDto> addPrizes(Set<PrizeDto> prizeDtos, QuestDto dto) throws Exception;

    GamificationUserDto retrieveTokenRewards(GamificationUserDto gamificationUserDto, Integer amount) throws Exception;
}
