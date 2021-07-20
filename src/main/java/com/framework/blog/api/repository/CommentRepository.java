package com.framework.blog.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.framework.blog.api.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
