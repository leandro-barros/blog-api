package com.framework.blog.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.framework.blog.api.model.Comment;
import com.framework.blog.api.model.Post;
import com.framework.blog.api.repository.PostRepository;
import com.framework.blog.api.service.CommentService;
import com.framework.blog.api.service.PostService;

@RestController
@RequestMapping("posts")
public class PostController {
	
	@Autowired
	private PostService postService;

	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private CommentService commentService;
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public void save(@RequestPart("post") Post post, @RequestPart MultipartFile[] files) {
		postService.save(post, files);
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/{idPost}/comment")
	public void saveComment(@PathVariable Long idPost, @RequestBody Comment comment) {
		commentService.save(idPost, comment);
	}
	
	@GetMapping
	public List<Post> lista() {
		return postRepository.findAll();
	}
	
	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		
	}
}
