package com.framework.blog.api.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.framework.blog.api.model.ImagesPosts;
import com.framework.blog.api.model.Post;
import com.framework.blog.api.repository.PostRepository;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;
	
	public void save(Post post, MultipartFile[] files) {
		
		for (MultipartFile multipartFile : files) {
			ImagesPosts imagePost = new ImagesPosts();
			
			try {
				imagePost.setImage(multipartFile.getBytes());
			} catch (IOException e) {
				throw new RuntimeException("Erro ao salvar imagem!");
			}
			
			imagePost.setPost(post);
			System.out.println("entrou"+imagePost.getImage());
			post.getImages().add(imagePost);
			
		}
		
		post.getComments().forEach(c -> c.setPost(post));
		post.getLinks().forEach(l -> l.setPost(post));
		
		postRepository.save(post);
	}
	
	public Post findPostById(Long id) {
		Optional<Post> postSaved = postRepository.findById(id);
		return postSaved.get();
	}
}
