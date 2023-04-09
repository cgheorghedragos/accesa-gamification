package ciurezu.gheorghe.dragos.accesa.gamification.repository;

import ciurezu.gheorghe.dragos.accesa.gamification.data.entity.Participant;
import ciurezu.gheorghe.dragos.accesa.gamification.data.entity.Quest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface QuestRepository extends CrudRepository<Quest, Long> {
    Quest findByTitle(String title);
    @Query(value = "SELECT * " +
            "FROM quest " +
            "WHERE is_active = true",nativeQuery = true)
    List<Quest> findAllActive();

}