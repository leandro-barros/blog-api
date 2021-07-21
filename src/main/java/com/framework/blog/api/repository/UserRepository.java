package com.framework.blog.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.framework.blog.api.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	public Optional<User> findByLogin(String login);
}
