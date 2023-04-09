package ciurezu.gheorghe.dragos.accesa.gamification.repository;

import ciurezu.gheorghe.dragos.accesa.gamification.data.entity.BadgeCategory;
import org.springframework.data.repository.CrudRepository;

public interface BadgeCategoryRepository extends CrudRepository<BadgeCategory, Long> {
    BadgeCategory findByCategoryName(String category);
}