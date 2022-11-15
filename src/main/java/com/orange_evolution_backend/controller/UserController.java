//Aqui serve como Endpoints para os metodos de userServicec

package com.orange_evolution_backend.controller;


import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orange_evolution_backend.dto.UserDTO;
import com.orange_evolution_backend.entity.User;
import com.orange_evolution_backend.repository.UserRepository;
import com.orange_evolution_backend.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

@CrossOrigin(origins = "*")
@RequestMapping("/users")
@RestController
@AllArgsConstructor
@Api(description = "User HTTP methods", tags = "Users")
public class UserController {

	// Aqui está chamando os serviços que serão necessários para o funcionamento  da classe, seus contrutores estão sendo gerados de "@AllArgosConstructor"
	private UserService userService;
	private UserRepository userRepository;
	private ModelMapper modelMapper;
	

	// Usa o serviço de UserService e e também os convertores de DTO para que retorne
    // todos os usuarios cadastrados no banco em formato de Usuario DTO.
	@ApiOperation(value = "Fetch all users")
	@GetMapping
	public ResponseEntity<List<UserDTO>> getAllUsers() {
		List<User> users = userService.findAllUsers();
		return ResponseEntity.ok(converUsersToListDTO(users));
	}
	
	// Usa o serviço de UserService e e também os convertores de DTO para que retorne
    // todos os usuarios cadastrados no banco em formato de Usuario DTO.
	@ApiOperation(value = "Fetch an user by ID")
	@GetMapping("/{userId}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable Long userId) {
		User user = userService.findUserById(userId);
		return ResponseEntity.ok(convertUserToDTO(user));
	}
	
	// Usa o serviço de UserService e os conversores DTO para
	// criar um novo usuario Recebe um corpo de Usuario DTO.
	// Deve retornar o Usuario DTO e uma resposta HTTP 201. 
	@ApiOperation(value = "Create and save an user")
	@PostMapping
	public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
		User user = converUserToEntity(userDTO);
		User saved = userService.saveUser(user);
		return new ResponseEntity<UserDTO>(convertUserToDTO(saved), HttpStatus.CREATED);
	}
	
	// Usa o servico de UserService e os conversores DTO para atualizar 
	// um usuario recebendo um corpo de Usuario DTO.
	// Deve retornar o usuario DTO e uma resposta HTTP 200.
	@ApiOperation(value = "Update an user")
	@PutMapping("/{userId}")
	public ResponseEntity<UserDTO> updateUser(@PathVariable Long userId, @RequestBody UserDTO userDTO) {
		if (!userRepository.existsById(userId)) {
			return ResponseEntity.notFound().build();
		}
		User user = converUserToEntity(userDTO);
		user.setId(userId);
		user = userService.saveUser(user);

		return ResponseEntity.ok(convertUserToDTO(user));
	}
	// Usa o servico de UserService para remover um Usuario
	// Recebe o ID usuario para remover.
	// Deve retornar uma resposta HTTP 204.
	@ApiOperation(value = "Delete an user")
	@DeleteMapping("/{userId}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
		if (!userRepository.existsById(userId)) {
			return ResponseEntity.notFound().build();
		}

		userService.deleteUser(userId);

		return ResponseEntity.noContent().build();
	}

	// Usa ModelMapper para converter uma Lista de Usuario
	// em uma lista de Usuario DTO.
    public List<UserDTO> converUsersToListDTO(List<User> users){
        List<UserDTO> returnUsersDTO = new ArrayList<>();
        users.forEach(user ->{
            returnUsersDTO.add(modelMapper.map(user, UserDTO.class));
        });
        return returnUsersDTO;
    }
	// Usa ModelMapper para converter um Usuario em Usuario DTO 
    public UserDTO convertUserToDTO(User user){
        return modelMapper.map(user, UserDTO.class);
    }

	// Usa ModelMapper para converter um Usuario DTO em Usuario
    public User converUserToEntity(UserDTO userDTO){
        return modelMapper.map(userDTO, User.class);
    }

}
