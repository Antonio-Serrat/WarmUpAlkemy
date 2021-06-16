package WUpAlkemy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import WUpAlkemy.entities.Blogger;
import WUpAlkemy.repository.BloggerRepository;

@Service
public class BloggerService {

	private BloggerRepository blgrRepo;

	@Autowired
	public BloggerService(BloggerRepository blgrRepo) {
		this.blgrRepo = blgrRepo;
	}

	public List<Blogger> findAll() {
		return (List<Blogger>) blgrRepo.findAll();
	}

	public Optional<Blogger> findById(Long id) {
		return blgrRepo.findById(id);
	}

	public void save(Blogger blogger) {
		blgrRepo.save(blogger);
	}

	public void deleteById(Long id) {
		blgrRepo.deleteById(id);
	}

}
