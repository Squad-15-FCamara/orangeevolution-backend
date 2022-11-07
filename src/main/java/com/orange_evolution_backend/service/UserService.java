package com.orange_evolution_backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

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
	
}
