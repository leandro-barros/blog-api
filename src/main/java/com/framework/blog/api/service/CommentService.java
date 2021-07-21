package com.framework.blog.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.framework.blog.api.model.Comment;
import com.framework.blog.api.model.User;
import com.framework.blog.api.repository.CommentRepository;
import com.framework.blog.api.repository.UserRepository;
import com.framework.blog.api.service.exception.DeletePermissionException;

@Service
public class CommentService {

	@Autowired
	private PostService postService;

	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public void save(Long idPost, Comment comment) {
		Optional<User> user = userRepository.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
		comment.setUser(user.get());
		
		comment.setPost(postService.findPostById(idPost));
		commentRepository.save(comment);		
	}
	
	public Comment findCommentById(Long id) {
		Optional<Comment> commentSaved = commentRepository.findById(id);
		if (!commentSaved.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}
		return commentSaved.get();
	}
	
	public void delete(Long id) {
		Comment comment = findCommentById(id);
		Optional<User> user = userRepository.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
	
		if (!comment.getUser().equals(user.get())) {
			throw new DeletePermissionException();
		}
		
		commentRepository.deleteById(id);
	}
	

}
