package WUpAlkemy.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import WUpAlkemy.entities.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

}
