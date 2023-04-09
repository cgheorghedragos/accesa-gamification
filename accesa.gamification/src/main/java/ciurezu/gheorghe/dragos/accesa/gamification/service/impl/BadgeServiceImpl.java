package ciurezu.gheorghe.dragos.accesa.gamification.service.impl;

import ciurezu.gheorghe.dragos.accesa.gamification.data.entity.Badge;
import ciurezu.gheorghe.dragos.accesa.gamification.data.entity.BadgeCategory;
import ciurezu.gheorghe.dragos.accesa.gamification.data.entity.BadgeUser;
import ciurezu.gheorghe.dragos.accesa.gamification.data.entity.GamificationUser;
import ciurezu.gheorghe.dragos.accesa.gamification.data.shared.BadgeCategoryDto;
import ciurezu.gheorghe.dragos.accesa.gamification.data.shared.BadgeDto;
import ciurezu.gheorghe.dragos.accesa.gamification.data.shared.GamificationUserDto;
import ciurezu.gheorghe.dragos.accesa.gamification.exceptions.ConflictException;
import ciurezu.gheorghe.dragos.accesa.gamification.repository.BadgeCategoryRepository;
import ciurezu.gheorghe.dragos.accesa.gamification.repository.BadgeRepository;
import ciurezu.gheorghe.dragos.accesa.gamification.repository.UserRepository;
import ciurezu.gheorghe.dragos.accesa.gamification.service.BadgeService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BadgeServiceImpl implements BadgeService {
    private final ModelMapper mapper;
    private final BadgeCategoryRepository badgeCategoryRepository;
    private final BadgeRepository badgeRepository;

    private final UserRepository userRepository;
    private final String ALREADY_EXISTS_ERROR_MESSAGE = "Item already exists!";

    @Override
    public BadgeCategoryDto saveBadgeCategory(BadgeCategoryDto badgeCategoryDto) throws ConflictException {
        if (badgeCategoryDto.getCategoryName() == null) {
            return null;
        }

        BadgeCategory badgeCategory = badgeCategoryRepository.findByCategoryName(badgeCategoryDto.getCategoryName());

        if (badgeCategory == null) {
            badgeCategory = mapper.map(badgeCategoryDto, BadgeCategory.class);
            badgeCategory = badgeCategoryRepository.save(badgeCategory);

            badgeCategoryDto = mapper.map(badgeCategory, BadgeCategoryDto.class);

            return badgeCategoryDto;
        }
        throw new ConflictException(ALREADY_EXISTS_ERROR_MESSAGE);
    }

    @Override
    public BadgeDto saveBadge(BadgeDto badgeDto) throws ConflictException {
        if (badgeDto.getBadgeCategory() == null || badgeDto.getImgUrl() == null || badgeDto.getLevel() == null || badgeDto.getMaxValue() == null) {
            return null;
        }
        BadgeCategory badgeCategory = badgeCategoryRepository.findByCategoryName(badgeDto.getBadgeCategory().getCategoryName());

        if (badgeCategory == null) {
            return null;
        }
        Badge badge = badgeRepository.findByBadgeCategoryAndLevel(badgeDto.getBadgeCategory().getCategoryName(), badgeDto.getLevel());

        if (badge == null) {
            badge = mapper.map(badgeDto, Badge.class);
            badge.setBadgeCategory(badgeCategory);
            badge = badgeRepository.save(badge);
            return mapper.map(badge, BadgeDto.class);
        }

        throw new ConflictException(ALREADY_EXISTS_ERROR_MESSAGE);
    }

    @Override
    public List<BadgeDto> getAllMyBadge(GamificationUserDto userDto) {
        GamificationUser user = userRepository.findByUsername(userDto.getUsername());

        return user.getBadges().stream()
                .map(badgeUser -> mapper.map(badgeUser.getBadge(), BadgeDto.class))
                .collect(Collectors.toList());
    }
}
