package WUpAlkemy.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import WUpAlkemy.entities.Post;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {

}
