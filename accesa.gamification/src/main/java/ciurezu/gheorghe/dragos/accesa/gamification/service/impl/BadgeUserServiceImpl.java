package ciurezu.gheorghe.dragos.accesa.gamification.service.impl;

import ciurezu.gheorghe.dragos.accesa.gamification.data.entity.Badge;
import ciurezu.gheorghe.dragos.accesa.gamification.data.entity.BadgeUser;
import ciurezu.gheorghe.dragos.accesa.gamification.data.entity.GamificationUser;
import ciurezu.gheorghe.dragos.accesa.gamification.data.shared.BadgeDto;
import ciurezu.gheorghe.dragos.accesa.gamification.data.shared.BadgeUserDto;
import ciurezu.gheorghe.dragos.accesa.gamification.data.shared.GamificationUserDto;
import ciurezu.gheorghe.dragos.accesa.gamification.data.shared.PrizeDto;
import ciurezu.gheorghe.dragos.accesa.gamification.repository.BadgeRepository;
import ciurezu.gheorghe.dragos.accesa.gamification.repository.BadgeUserRepository;
import ciurezu.gheorghe.dragos.accesa.gamification.repository.UserRepository;
import ciurezu.gheorghe.dragos.accesa.gamification.service.BadgeUserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.jsf.el.SpringBeanFacesELResolver;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class BadgeUserServiceImpl implements BadgeUserService {
    private final ModelMapper mapper;
    private final BadgeRepository badgeRepository;
    private final UserRepository userRepository;
    private final BadgeUserRepository badgeUserRepository;

    @Override
    public BadgeUserDto addUserBadge(BadgeUserDto badgeUserDto) {
        Integer level = badgeUserDto.getBadge().getLevel();
        String category = badgeUserDto.getBadge().getBadgeCategory().getCategoryName();
        String username = badgeUserDto.getUser().getUsername();

        Badge badge = badgeRepository.findByBadgeCategoryAndLevel(category,level);
        GamificationUser user = userRepository.findByUsername(username);

        BadgeUser badgeUser = badgeUserRepository.findByBadgeAndAndUser(badge,user);

        if(badgeUser == null){
            badgeUser = new BadgeUser();

            badgeUser.setBadge(badge);
            badgeUser.setUser(user);
            badgeUser.setCurrentProgress(0);
            badgeUser = badgeUserRepository.save(badgeUser);

            return mapper.map(badgeUser,BadgeUserDto.class);
        }

        return null;
    }
}
