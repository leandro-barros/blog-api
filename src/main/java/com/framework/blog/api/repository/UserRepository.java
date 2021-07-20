package com.framework.blog.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.framework.blog.api.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
