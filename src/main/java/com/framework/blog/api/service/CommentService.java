package com.framework.blog.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.blog.api.model.Comment;
import com.framework.blog.api.repository.CommentRepository;

@Service
public class CommentService {

	@Autowired
	private PostService postService;

	@Autowired
	private CommentRepository commentRepository;
	
	public void save(Long idPost, Comment comment) {
		comment.setPost(postService.findPostById(idPost));
		commentRepository.save(comment);		
	}
	

}
