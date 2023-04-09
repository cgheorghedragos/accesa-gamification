package ciurezu.gheorghe.dragos.accesa.gamification.repository;

import ciurezu.gheorghe.dragos.accesa.gamification.data.entity.Badge;
import ciurezu.gheorghe.dragos.accesa.gamification.data.entity.BadgeUser;
import ciurezu.gheorghe.dragos.accesa.gamification.data.entity.GamificationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface BadgeUserRepository extends CrudRepository<BadgeUser, Long> {
    BadgeUser findByBadgeAndAndUser(Badge badge, GamificationUser user);
    BadgeUser findByBadge_BadgeCategory_CategoryNameAndUserUsername(String badgeCategory, String username);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO badge_user(current_progress,badge_id,users_id) VALUES (?1,?2,?3)",nativeQuery = true)
    void saveBadgeUser(Integer currentProgress,Long badgeId, Long usersId);
    Integer deleteByBadgeAndUser(Badge badge,GamificationUser user);
}