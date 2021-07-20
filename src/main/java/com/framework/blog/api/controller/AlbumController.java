package com.framework.blog.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.framework.blog.api.model.Album;
import com.framework.blog.api.service.AlbumService;

@RestController
@RequestMapping("albuns")
public class AlbumController {
	
	@Autowired
	private AlbumService albumService;
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public void save(@RequestPart("album") Album album, @RequestPart MultipartFile[] files) {
		albumService.save(album, files);
	}
	
}
