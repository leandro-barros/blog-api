package com.framework.blog.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.blog.api.model.User;
import com.framework.blog.api.repository.UserRepository;

@Service
public class UserService {

//	@Autowired
//	PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	public void save(User user) {
		userRepository.save(user);
	}
	
}
