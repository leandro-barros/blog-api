package com.framework.blog.api.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.framework.blog.api.model.ImagesPosts;
import com.framework.blog.api.model.Post;
import com.framework.blog.api.model.User;
import com.framework.blog.api.repository.PostRepository;
import com.framework.blog.api.repository.UserRepository;
import com.framework.blog.api.service.exception.DeletePermissionException;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public void save(Post post, MultipartFile[] files) {
		Optional<User> user = userRepository.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
		post.setUser(user.get());
		
		for (MultipartFile multipartFile : files) {
			ImagesPosts imagePost = new ImagesPosts();
			
			try {
				imagePost.setImage(multipartFile.getBytes());
			} catch (IOException e) {
				throw new RuntimeException("Erro ao salvar imagem!");
			}
			
			imagePost.setPost(post);
			post.getImages().add(imagePost);
			
		}
		
		post.getLinks().forEach(l -> l.setPost(post));
		
		postRepository.save(post);
	}
	
	public Post findPostById(Long id) {
		Optional<Post> postSaved = postRepository.findById(id);
		if (!postSaved.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}
		return postSaved.get();
	}
	
	public void delete(Long id) {
		Post post = findPostById(id);
		Optional<User> user = userRepository.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
		
		if (!post.getUser().equals(user.get())) {
			throw new DeletePermissionException();
		}
		
		postRepository.deleteById(id);
	}
}
