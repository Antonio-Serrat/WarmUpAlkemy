package WUpAlkemy.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import WUpAlkemy.entities.Post;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {

	List<Post> findByTitle(String title);

	List<Post> findByCategory(String category);

	List<Post> findByTitleAndCategory(String title, String category);

}
