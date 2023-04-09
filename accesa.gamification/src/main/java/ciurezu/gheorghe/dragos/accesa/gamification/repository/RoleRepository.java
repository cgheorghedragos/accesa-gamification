package ciurezu.gheorghe.dragos.accesa.gamification.repository;

import ciurezu.gheorghe.dragos.accesa.gamification.data.entity.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByName(String name);
}