// Este Servico serve para operacoes relacionadas com Usuarios.

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
	
	// Aqui está chamando os serviços que serão necessários para o funcionamento  da classe, seus contrutores estão sendo gerados de "@AllArgosConstructor"	
	private UserRepository userRepository;
	
	// Busca todos os Usuarios.
	public List<User> findAllUsers() {
		
		return userRepository.findAll();
	}
	

	// Busca um usuario por ID.
	public User findUserById(Long userId) {
		
		return userRepository.findById(userId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, 
						"Este usuário não existe!"));
	}
	
	// Salva um novo Usuario.
	public User saveUser(User user) {
		
		userRepository.save(user);
		return user;
	}
	
	// Deleta um Usuario.
	public void deleteUser(Long userId) {
		
		userRepository.deleteById(userId);
	}
	
}
