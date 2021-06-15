package WUpAlkemy.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import WUpAlkemy.entities.Blogger;

@Repository
public interface BloggerRepository extends CrudRepository<Blogger, Long> {

}
