package com.orange_evolution_backend.controller;

import java.util.List;

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
@Api(value = "User HTTP methods")
public class UserController {

	private UserService userService;
	private UserRepository userRepository;
	
	@ApiOperation(value = "Fetch all users")
	@GetMapping
	public ResponseEntity<List<User>> getAllUsers() {

		return ResponseEntity.ok(userService.findAllUsers());
	}
	
	@ApiOperation(value = "Fetch an user by ID")
	@GetMapping("/{userId}")
	public ResponseEntity<User> getUserById(@PathVariable Long userId) {

		return ResponseEntity.ok(userService.findUserById(userId));
	}
	
	@ApiOperation(value = "Create and save an user")
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user) {

		return new ResponseEntity<User>(userService.saveUser(user), HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "Update an user")
	@PutMapping("/{userId}")
	public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody User user) {
		if (!userRepository.existsById(userId)) {
			return ResponseEntity.notFound().build();
		}

		user.setId(userId);
		user = userService.saveUser(user);

		return ResponseEntity.ok(user);
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

}
