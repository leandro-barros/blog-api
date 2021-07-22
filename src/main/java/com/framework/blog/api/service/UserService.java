package com.framework.blog.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.framework.blog.api.model.User;
import com.framework.blog.api.repository.UserRepository;
import com.framework.blog.api.service.exception.LoginRegisteredException;

@Service
public class UserService {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	public void save(User user) {
		if (userRepository.findByLogin(user.getLogin()).isPresent()) {
			throw new LoginRegisteredException();
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
	}
	
}
