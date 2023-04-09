package ciurezu.gheorghe.dragos.accesa.gamification.service.impl;

import ciurezu.gheorghe.dragos.accesa.gamification.data.entity.*;
import ciurezu.gheorghe.dragos.accesa.gamification.data.shared.*;
import ciurezu.gheorghe.dragos.accesa.gamification.data.shared.specific_dto.PrizeWithBadgesDto;
import ciurezu.gheorghe.dragos.accesa.gamification.exceptions.BadRequestException;
import ciurezu.gheorghe.dragos.accesa.gamification.repository.*;
import ciurezu.gheorghe.dragos.accesa.gamification.service.BadgeForRewardService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BadgeForRewardServiceImpl implements BadgeForRewardService {
    private final BadgeForRewardRepository badgeForRewardRepository;
    private final BadgeRepository badgeRepository;
    private final ModelMapper mapper;
    private final ParticipantRepository participantRepository;
    private final PrizeRepository prizeRepository;
    private final UserRepository userRepository;
    private final BadgeUserRepository badgeUserRepository;
    private final WinnerRepository winnerRepository;
    private final QuestRepository questRepository;


    @Override
    public void transferBadgeFromUserToReward(Set<PrizeDto> prizeDtos, QuestDto questDto) {
        prizeDtos.forEach(
                prizeDto -> {
                    prizeDto.getBadgeForRewards().forEach(badgeForRewardDto -> {

                        Prize prize = prizeRepository.findByLongId(prizeDto.getId());
                        GamificationUser user = userRepository.findByUsername(questDto.getUser().getUsername());
                        BadgeDto badgeDto = badgeForRewardDto.getBadge();
                        BadgeCategoryDto badgeCategoryDto = badgeDto.getBadgeCategory();
                        Badge badge = badgeRepository.findByBadgeCategoryAndLevel(badgeCategoryDto.getCategoryName(), badgeDto.getLevel());

                        // Delete Badge from User
                        Integer deleted = badgeUserRepository.deleteByBadgeAndUser(badge,user);

                        // Transfer to Reward
                        BadgeForReward badgeForReward = new BadgeForReward();
                        badgeForReward.setBadge(badge);
                        badgeForReward.setPrize(prize);

                        badgeForRewardRepository.save(badgeForReward);

                    });
                });
    }

    @Override
    public void transferBadgeFromRewardToUser(List<PrizeWithBadgesDto> prizeDtos, List<ParticipantDto> participantDtoList, QuestDto questDto) throws BadRequestException {
        if(prizeDtos == null || participantDtoList == null){
            throw new BadRequestException("Data not found!");
        }
        if (participantDtoList.size() == 0){
            throw new BadRequestException("No winners received!");
        }
        if (prizeDtos.size() != participantDtoList.size()){
            throw new BadRequestException("Size of winners is different from numbers of winners");
        }

        Quest quest = questRepository.findByTitle(questDto.getTitle());

        if (quest == null){
            throw new BadRequestException("Quest not found");
        }

        for (int index= 0; index < prizeDtos.size() && index < participantDtoList.size() ; index ++){

            ParticipantDto participantDto = participantDtoList.get(index);
            PrizeWithBadgesDto prizeDto = prizeDtos.get(index);

            if(  participantDto.getUser() == null){
                throw new BadRequestException("Data not found");
            }

            // extract participant
            Participant participant = participantRepository.findByUserUsernameAndQuestTitle(
                    participantDto.getUser().getUsername(),quest.getTitle());
            // extract prize
            Prize prize = prizeRepository.findByLongId(prizeDtos.get(index).getId());

            if(prize == null || participant == null){
                throw new BadRequestException("Try again later");
            }

            // give userReward
            GamificationUser user = participant.getUser();
            user.setTokens(user.getTokens()+ prizeDto.getTokens());
            user.setNrSolvedQuests(user.getNrSolvedQuests()+1);
            userRepository.save(user);

            if(prize.getBadgeForRewards() != null){
                for (BadgeForReward badgeFR : prize.getBadgeForRewards()){
                    Badge badge = badgeFR.getBadge();
                    String categoryBadgeFromReward = badge.getBadgeCategory().getCategoryName();
                    String username = user.getUsername();

                    BadgeUser userBadge = badgeUserRepository.findByBadge_BadgeCategory_CategoryNameAndUserUsername(categoryBadgeFromReward,username);

                    if (userBadge == null){
                        userBadge = new BadgeUser();
                        userBadge.setUser(user);
                        userBadge.setBadge(badge);
                        badgeUserRepository.save(userBadge);
                    }else{
                        Integer currentLevel = userBadge.getBadge().getLevel();
                        Integer newLevel = badge.getLevel();

                        if (currentLevel < newLevel){
                            userBadge.setBadge(badge);
                            userBadge.setCurrentProgress(0);
                            badgeUserRepository.save(userBadge);
                        }
                    }
                }
            }
            //save winner
            Winner winner = new Winner();
            winner.setPrize(prize);
            winner.setParticipant(participant);
            winnerRepository.save(winner);

            // make quest inactive
            quest.setIsActive(false);
            questRepository.save(quest);
        }
    }
}
