package ciurezu.gheorghe.dragos.accesa.gamification.repository;

import ciurezu.gheorghe.dragos.accesa.gamification.data.entity.GamificationUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<GamificationUser, Long> {
    GamificationUser findByUsername(String username);
    @Query(value = "SELECT * " +
            "FROM users " +
            "ORDER BY nr_solved_quests " +
            "ASC " +
            "FETCH FIRST ?1 ROW ONLY ",nativeQuery = true)

    List<GamificationUser> getTopByQuestSolved(Integer topNumbers);
}