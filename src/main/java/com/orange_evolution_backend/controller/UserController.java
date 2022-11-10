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

	private UserService userService;
	private UserRepository userRepository;
	private ModelMapper modelMapper;
	
	@ApiOperation(value = "Fetch all users")
	@GetMapping
	public ResponseEntity<List<UserDTO>> getAllUsers() {
		List<User> users = userService.findAllUsers();
		return ResponseEntity.ok(converUsersToListDTO(users));
	}
	
	@ApiOperation(value = "Fetch an user by ID")
	@GetMapping("/{userId}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable Long userId) {
		User user = userService.findUserById(userId);
		return ResponseEntity.ok(convertUserToDTO(user));
	}
	
	@ApiOperation(value = "Create and save an user")
	@PostMapping
	public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
		User user = converUserToEntity(userDTO);
		User saved = userService.saveUser(user);
		return new ResponseEntity<UserDTO>(convertUserToDTO(saved), HttpStatus.CREATED);
	}
	
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
	
	@ApiOperation(value = "Delete an user")
	@DeleteMapping("/{userId}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
		if (!userRepository.existsById(userId)) {
			return ResponseEntity.notFound().build();
		}

		userService.deleteUser(userId);

		return ResponseEntity.noContent().build();
	}

	
    public List<UserDTO> converUsersToListDTO(List<User> users){
        List<UserDTO> returnUsersDTO = new ArrayList<>();
        users.forEach(user ->{
            returnUsersDTO.add(modelMapper.map(user, UserDTO.class));
        });
        return returnUsersDTO;
    }

    public UserDTO convertUserToDTO(User user){
        return modelMapper.map(user, UserDTO.class);
    }
    public User converUserToEntity(UserDTO userDTO){
        return modelMapper.map(userDTO, User.class);
    }

}
