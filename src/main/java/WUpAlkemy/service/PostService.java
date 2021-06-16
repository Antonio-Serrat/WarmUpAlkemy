package WUpAlkemy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import WUpAlkemy.entities.Post;
import WUpAlkemy.repository.PostRepository;

@Service
public class PostService {

	private PostRepository postRepo;

	@Autowired
	public PostService(PostRepository postRepo) {
		this.postRepo = postRepo;
	}

	public List<Post> findAll() {
		return (List<Post>) postRepo.findAll();
	}

	public Optional<Post> findById(Long id) {
		return postRepo.findById(id);
	}

	public void save(Post post) {
		postRepo.save(post);
	}

	public void deleteById(Long id) {
		postRepo.deleteById(id);
	}

}
