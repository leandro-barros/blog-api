package com.framework.blog.api.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.framework.blog.api.model.Album;
import com.framework.blog.api.model.ImagesPosts;
import com.framework.blog.api.model.User;
import com.framework.blog.api.repository.AlbumRepository;
import com.framework.blog.api.repository.UserRepository;

@Service
public class AlbumService {
	
	@Autowired
	private AlbumRepository albumRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public void save(Album album, MultipartFile[] files) {
		
		for (MultipartFile multipartFile : files) {
			ImagesPosts imagePost = new ImagesPosts();
			
			try {
				imagePost.setImage(multipartFile.getBytes());
			} catch (IOException e) {
				throw new RuntimeException("Erro ao salvar imagem!");
			}
			
			imagePost.setAlbum(album);
			album.getImages().add(imagePost);
			
		}
		
		albumRepository.save(album);
	}
	
	public Album findAlbumById(Long id) {
		Optional<Album> albumSaved = albumRepository.findById(id);
		return albumSaved.get();
	}
	
	public void delete(Long id) {
		Album album = findAlbumById(id);
		Optional<User> user = userRepository.findByLogin(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		
		if (album.getUser().getId().equals(user.get().getId())) {
			albumRepository.deleteById(id);
		}
	}

}
