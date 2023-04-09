package ciurezu.gheorghe.dragos.accesa.gamification.repository;

import ciurezu.gheorghe.dragos.accesa.gamification.data.entity.Prize;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PrizeRepository extends CrudRepository<Prize, Long> {
    @Query(value = "Select * " +
            "FROM prize " +
            "WHERE prize.id = ?1 " +
            "FETCH FIRST 1 ROW ONLY ", nativeQuery = true)
    Prize findByLongId(Long id);
}