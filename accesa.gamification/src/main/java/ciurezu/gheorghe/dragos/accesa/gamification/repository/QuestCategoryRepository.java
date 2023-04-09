package ciurezu.gheorghe.dragos.accesa.gamification.repository;

import ciurezu.gheorghe.dragos.accesa.gamification.data.entity.QuestCategory;
import org.springframework.data.repository.CrudRepository;

public interface QuestCategoryRepository extends CrudRepository<QuestCategory, Long> {
    QuestCategory findByCategoryName(String category);
}