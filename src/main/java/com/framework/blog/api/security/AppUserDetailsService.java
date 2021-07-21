package com.framework.blog.api.security;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.framework.blog.api.model.User;
import com.framework.blog.api.repository.UserRepository;

@Service
public class AppUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		Optional<User> userOptional = userRepository.findByLogin(login);
		User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("Login e/ou senha inválido(a)!"));
		Set<SimpleGrantedAuthority> authorities = new HashSet<SimpleGrantedAuthority>();
		return new org.springframework.security.core.userdetails.User(login, user.getPassword(), authorities);
	}

}
