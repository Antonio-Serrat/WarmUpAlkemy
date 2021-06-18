package WUpAlkemy.controller;

import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import WUpAlkemy.entities.Post;
import WUpAlkemy.service.PostService;

@RestController
@RequestMapping(value = "/view")
public class PostsController {

	private PostService servicePost;

	@Autowired
	public PostsController(PostService servicePost) {
		this.servicePost = servicePost;
	}

	@GetMapping(value = "/posts")
	@ResponseBody
	public List<Post> allPosts() {
		return servicePost.findAll();

	}

	@GetMapping(value = "/posts?title=TITLE")
	@ResponseBody
	public ResponseEntity<List<Post>> postByTitle(@PathParam("TITLE") String title) {
		return new ResponseEntity<>(servicePost.findByTitle(title), HttpStatus.OK);
	}

	@GetMapping(value = "/posts?category=CATEGORY")
	@ResponseBody
	public ResponseEntity<List<Post>> postsByCategory(@PathParam("CATEGORY") String category) {
		return new ResponseEntity<>(servicePost.findByCategory(category), HttpStatus.OK);
	}

	@GetMapping(value = "/posts?title=TITLE&category=CATEGORY")
	@ResponseBody
	public ResponseEntity<List<Post>> postsByTitleAndCategory(@PathParam("TITLE") String title,
			@PathParam("CATEGORY") String category) {
		return new ResponseEntity<>(servicePost.findByTitleAndCategory(title, category), HttpStatus.OK);
	}

	@GetMapping(value = "/posts/{id}")
	@ResponseBody
	public ResponseEntity<Optional<Post>> postsById(@PathParam("id") Long id) {
		return new ResponseEntity<>(servicePost.findById(id), HttpStatus.OK);

	}

	@PostMapping(value = "/post")
	@ResponseBody
	public ResponseEntity<Post> newPost(@RequestBody Post newPost) {

//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		Account acc = (Account) auth.getPrincipal();

		Post post = new Post();
		post.setTitle(newPost.getTitle());
		post.setCategory(newPost.getCategory());
		post.setDate(newPost.getDate());
		post.setContained(newPost.getContained());
		post.setImageUrl(newPost.getImageUrl());
//		post.setBlogger(acc.getBlogger());

		servicePost.save(post);

		return new ResponseEntity<>(post, HttpStatus.CREATED);
	}

	@PatchMapping(value = "/updatePosts/{id}")
	@ResponseBody
	public ResponseEntity<Post> updatePost(@PathParam("id") Long id, @RequestBody Post upPost) {

		Post oldPost = servicePost.findById(id).get();

		if (upPost.getTitle().isEmpty()) {
		} else {
			oldPost.setTitle(upPost.getTitle());
		}
		if (upPost.getCategory().isEmpty()) {
		} else {
			oldPost.setCategory(upPost.getCategory());
		}
		if (upPost.getContained().isEmpty()) {
		} else {
			oldPost.setContained(upPost.getContained());
		}
		if (upPost.getImageUrl().isEmpty()) {
		} else {
			oldPost.setImageUrl(upPost.getImageUrl());
		}
		if (upPost.getTitle().isEmpty()) {
		} else {
			oldPost.setTitle(upPost.getTitle());
		}
		oldPost.setDate(upPost.getDate());

		return new ResponseEntity<>(upPost, HttpStatus.ACCEPTED);
	}

}
