package ciurezu.gheorghe.dragos.accesa.gamification.service;

import ciurezu.gheorghe.dragos.accesa.gamification.data.entity.BadgeUser;
import ciurezu.gheorghe.dragos.accesa.gamification.data.shared.BadgeDto;
import ciurezu.gheorghe.dragos.accesa.gamification.data.shared.BadgeUserDto;
import ciurezu.gheorghe.dragos.accesa.gamification.data.shared.GamificationUserDto;
import ciurezu.gheorghe.dragos.accesa.gamification.data.shared.PrizeDto;

import java.util.Set;

public interface BadgeUserService {
    BadgeUserDto addUserBadge(BadgeUserDto badgeUserDto);
}
