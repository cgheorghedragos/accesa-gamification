package ciurezu.gheorghe.dragos.accesa.gamification.service.impl;

import ciurezu.gheorghe.dragos.accesa.gamification.data.StandardQuestCategory;
import ciurezu.gheorghe.dragos.accesa.gamification.data.entity.Badge;
import ciurezu.gheorghe.dragos.accesa.gamification.data.entity.BadgeUser;
import ciurezu.gheorghe.dragos.accesa.gamification.data.entity.Bird;
import ciurezu.gheorghe.dragos.accesa.gamification.data.entity.GamificationUser;
import ciurezu.gheorghe.dragos.accesa.gamification.data.remote_respone.AlexFlipNoteApiResponse;
import ciurezu.gheorghe.dragos.accesa.gamification.data.shared.GamificationUserDto;
import ciurezu.gheorghe.dragos.accesa.gamification.exceptions.BadRequestException;
import ciurezu.gheorghe.dragos.accesa.gamification.exceptions.NotAllServersAreOnException;
import ciurezu.gheorghe.dragos.accesa.gamification.repository.BadgeRepository;
import ciurezu.gheorghe.dragos.accesa.gamification.repository.BadgeUserRepository;
import ciurezu.gheorghe.dragos.accesa.gamification.repository.BirdRepository;
import ciurezu.gheorghe.dragos.accesa.gamification.repository.UserRepository;
import ciurezu.gheorghe.dragos.accesa.gamification.service.BirdService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class BirdServiceImpl implements BirdService {
    private final String gettingBirdUrl = "https://api.alexflipnote.dev/birb";
    private final Integer lovedBirdUpdateTokens = 1;
    private final BirdRepository birdRepository;
    private final BadgeUserRepository badgeUserRepository;
    private final BadgeRepository badgeRepository;
    private final UserRepository userRepository;

    @Override
    public String getAnotherBird(GamificationUserDto userDto) throws Exception {
        RestTemplate restTemplate = new RestTemplate();

        AlexFlipNoteApiResponse birdFromApi = restTemplate.getForObject(gettingBirdUrl, AlexFlipNoteApiResponse.class);
        if (birdFromApi == null) {
            throw new NotAllServersAreOnException("Something went wrong");
        }
        if (birdFromApi.getFile() == null) {
            throw new NotAllServersAreOnException("Something went wrong");
        }

        if (userDto == null) {
            throw new BadRequestException("Data not found");
        }
        if (userDto.getUsername() == null) {
            throw new BadRequestException("Username data is empty!");
        }

        Bird bird = birdRepository.findByUserUsername(userDto.getUsername());
        bird.setImgUrl(birdFromApi.getFile());
        birdRepository.save(bird);
        return birdFromApi.getFile();
    }

    @Override
    public String getMyBird(GamificationUserDto userDto) throws Exception {
        if (userDto == null) {
            throw new BadRequestException("Data not found");
        }
        if (userDto.getUsername() == null) {
            throw new BadRequestException("Username data is empty!");
        }
        Bird bird = birdRepository.findByUserUsername(userDto.getUsername());

        if (bird == null) {
            throw new BadRequestException("Username not found!");
        }
        String birdImg = bird.getImgUrl();

        if (birdImg == null) {
            birdImg = getAnotherBird(userDto);
            birdRepository.save(bird);
        }
        bird.setImgUrl(birdImg);

        return birdImg;
    }

    @Override
    public String lovedBird(GamificationUserDto userDto) throws Exception {
        if (userDto == null) {
            throw new BadRequestException("Data not found");
        }
        if (userDto.getUsername() == null) {
            throw new BadRequestException("Username data is empty!");
        }
        Bird bird = birdRepository.findByUserUsername(userDto.getUsername());

        if (bird == null) {
            throw new BadRequestException("Username not found!");
        }

        GamificationUser user = bird.getUser();
        BadgeUser badgeUser = badgeUserRepository.findByBadge_BadgeCategory_CategoryNameAndUserUsername(StandardQuestCategory.birdLover, userDto.getUsername());

        if (badgeUser == null) {
            Badge badge = badgeRepository.findByBadgeCategoryAndLevel(StandardQuestCategory.birdLover, 0);
            badgeUserRepository.saveBadgeUser(0, badge.getId(), user.getId());
            badgeUser = badgeUserRepository.findByBadge_BadgeCategory_CategoryNameAndUserUsername(StandardQuestCategory.birdLover, userDto.getUsername());
            user.getBadges().add(badgeUser);
        }
        badgeUser.setCurrentProgress(badgeUser.getCurrentProgress() + 1);

        if ( badgeUser.getCurrentProgress() >= badgeUser.getBadge().getMaxValue() ) {
            String category = badgeUser.getBadge().getBadgeCategory().getCategoryName();
            Integer level = badgeUser.getBadge().getLevel() + 1;
            Badge badge = badgeRepository.findByBadgeCategoryAndLevel(category,level);
            if(badge != null){
                badgeUser.setBadge(badge);
                badgeUser.setCurrentProgress(0);
            }
        }
        user.setTokens(user.getTokens() + lovedBirdUpdateTokens);
        userRepository.save(user);

        GamificationUserDto userDto1 = new GamificationUserDto();
        userDto1.setUsername(user.getUsername());
        return getAnotherBird(userDto1);
    }
}
