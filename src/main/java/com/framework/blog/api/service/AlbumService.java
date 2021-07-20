package com.framework.blog.api.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.framework.blog.api.model.Album;
import com.framework.blog.api.model.ImagesPosts;
import com.framework.blog.api.repository.AlbumRepository;

@Service
public class AlbumService {
	
	@Autowired
	private AlbumRepository albumRepository;
	
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

}
