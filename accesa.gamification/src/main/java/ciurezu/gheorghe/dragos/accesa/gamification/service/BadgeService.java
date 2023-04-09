package ciurezu.gheorghe.dragos.accesa.gamification.service;

import ciurezu.gheorghe.dragos.accesa.gamification.data.entity.Badge;
import ciurezu.gheorghe.dragos.accesa.gamification.data.entity.GamificationUser;
import ciurezu.gheorghe.dragos.accesa.gamification.data.shared.BadgeCategoryDto;
import ciurezu.gheorghe.dragos.accesa.gamification.data.shared.BadgeDto;
import ciurezu.gheorghe.dragos.accesa.gamification.data.shared.GamificationUserDto;
import ciurezu.gheorghe.dragos.accesa.gamification.exceptions.ConflictException;

import java.util.List;

public interface BadgeService {
    BadgeCategoryDto saveBadgeCategory(BadgeCategoryDto badgeCategoryDto) throws ConflictException;

    BadgeDto saveBadge(BadgeDto badge) throws ConflictException;

    List<BadgeDto> getAllMyBadge(GamificationUserDto userDto);

}
