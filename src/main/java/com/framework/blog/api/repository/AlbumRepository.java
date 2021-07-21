package com.framework.blog.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.framework.blog.api.model.Album;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {

	@Query(value = "SELECT a FROM Album a JOIN ImagesPosts i")
	public List<Album> findAllAlbuns();
}
