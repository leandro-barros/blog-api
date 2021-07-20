package com.framework.blog.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.framework.blog.api.model.Album;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {

}
