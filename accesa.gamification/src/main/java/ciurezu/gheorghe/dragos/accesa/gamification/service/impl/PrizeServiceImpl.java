package ciurezu.gheorghe.dragos.accesa.gamification.service.impl;

import ciurezu.gheorghe.dragos.accesa.gamification.data.entity.GamificationUser;
import ciurezu.gheorghe.dragos.accesa.gamification.data.entity.Prize;
import ciurezu.gheorghe.dragos.accesa.gamification.data.entity.Quest;
import ciurezu.gheorghe.dragos.accesa.gamification.data.shared.GamificationUserDto;
import ciurezu.gheorghe.dragos.accesa.gamification.data.shared.PrizeDto;
import ciurezu.gheorghe.dragos.accesa.gamification.data.shared.QuestDto;
import ciurezu.gheorghe.dragos.accesa.gamification.exceptions.BadRequestException;
import ciurezu.gheorghe.dragos.accesa.gamification.repository.PrizeRepository;
import ciurezu.gheorghe.dragos.accesa.gamification.repository.QuestRepository;
import ciurezu.gheorghe.dragos.accesa.gamification.repository.UserRepository;
import ciurezu.gheorghe.dragos.accesa.gamification.service.PrizeService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class PrizeServiceImpl implements PrizeService {
    private final PrizeRepository prizeRepository;
    private final QuestRepository questRepository;
    private final UserRepository userRepository;
    private final String TRY_AGAIN_LATER_MESSAGE = "Try Again Later!";
    private final ModelMapper mapper;

    @Override
    public PrizeDto addPrize(PrizeDto prizeDto) {
        System.out.println(prizeDto);
        Prize prize = mapper.map(prizeDto, Prize.class);
        PrizeDto dto = mapper.map(prize, PrizeDto.class);
        System.out.println(dto);
        return null;
    }

    @Override
    public Set<PrizeDto> addPrizes(Set<PrizeDto> prizeDtos, QuestDto questDto) throws Exception {

        if (questDto == null) {
            throw new BadRequestException("Data is null");

        }
        Quest quest = questRepository.findByTitle(questDto.getTitle());

        if (quest == null) {
            throw new BadRequestException(TRY_AGAIN_LATER_MESSAGE);
        }

        Set<PrizeDto> prizes = new HashSet<>();

        for (PrizeDto prizeDto : prizeDtos) {
            Prize prize = mapper.map(prizeDto, Prize.class);
            prize.setQuest(quest);

            if (prize.getTokens() == null) {
                throw new BadRequestException("Not enough tokens");
            }

            GamificationUserDto userDto = questDto.getUser();
            Integer tokens = prize.getTokens();

            //Extract tokens for owner
            retrieveTokenRewards(userDto, tokens);
            //Save token for rewards
            prize = prizeRepository.save(prize);

            PrizeDto prizeDTO = mapper.map(prize, PrizeDto.class);
            prizeDTO.setBadgeForRewards(prizeDto.getBadgeForRewards());
            prizes.add(prizeDTO);
        }
        ;

        return prizes;
    }

    @Override
    public GamificationUserDto retrieveTokenRewards(GamificationUserDto gamificationUserDto, Integer amount) throws Exception {
        if (gamificationUserDto == null) {
            throw new BadRequestException("Data not found!");
        }
        GamificationUser user = userRepository.findByUsername(gamificationUserDto.getUsername());

        if (user == null) {
            throw new BadRequestException("User not found!");
        }
        if(amount < 0){
            throw new BadRequestException("amount can't be negative");
        }

        int tokens = user.getTokens() + amount;

        if (tokens < 0) {
            throw new BadRequestException("Not enough money!");
        }

        user.setTokens(tokens);
        user = userRepository.save(user);

        return mapper.map(user, GamificationUserDto.class);
    }
}
