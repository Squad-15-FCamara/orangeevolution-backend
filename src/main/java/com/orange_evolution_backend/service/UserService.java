package com.orange_evolution_backend.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.orange_evolution_backend.entity.User;
import com.orange_evolution_backend.repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.Data;

@Service
@Data
@AllArgsConstructor
public class UserService {
	
	private UserRepository userRepository;
	
	public List<User> findAllUsers() {
		
		return userRepository.findAll();
	}
	
	public User findUserById(Long userId) {
		
		return userRepository.findById(userId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, 
						"Este usuário não existe!"));
	}
	
}
