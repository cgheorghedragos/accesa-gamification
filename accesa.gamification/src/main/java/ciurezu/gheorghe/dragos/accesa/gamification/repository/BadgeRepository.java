package ciurezu.gheorghe.dragos.accesa.gamification.repository;

import ciurezu.gheorghe.dragos.accesa.gamification.data.entity.Badge;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BadgeRepository extends CrudRepository<Badge, Long> {

    @Query(value = "SELECT bg.* " +
            " from badge bg INNER JOIN badge_category bg_cat " +
            " ON bg.badge_category_id = bg_cat.id " +
            " WHERE bg.level = ?2 " +
            " AND bg_cat.category_name = ?1 " +
            " LIMIT 1 ",nativeQuery = true)
    Badge findByBadgeCategoryAndLevel(String category, Integer level);
}