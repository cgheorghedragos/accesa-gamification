package ciurezu.gheorghe.dragos.accesa.gamification.repository;

import ciurezu.gheorghe.dragos.accesa.gamification.data.entity.Bird;
import org.springframework.data.repository.CrudRepository;

public interface BirdRepository extends CrudRepository<Bird, Long> {

    Bird findByUserUsername(String username);
}