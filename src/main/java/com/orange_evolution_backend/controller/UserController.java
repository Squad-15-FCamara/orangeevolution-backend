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

import lombok.AllArgsConstructor;

@CrossOrigin(origins = "*")
@RequestMapping("/users")
@RestController
@AllArgsConstructor
public class UserController {

	private UserService userService;
	private UserRepository userRepository;

	@GetMapping
	public ResponseEntity<List<User>> getAllUsers() {

		return ResponseEntity.ok(userService.findAllUsers());
	}

	@GetMapping("/{userId}")
	public ResponseEntity<User> getUserById(@PathVariable Long userId) {

		return ResponseEntity.ok(userService.findUserById(userId));
	}

	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user) {

		return new ResponseEntity<User>(userService.saveUser(user), HttpStatus.CREATED);
	}

	@PutMapping("/{userId}")
	public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody User user) {
		if (!userRepository.existsById(userId)) {
			return ResponseEntity.notFound().build();
		}

		user.setId(userId);
		user = userService.saveUser(user);

		return ResponseEntity.ok(user);
	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
		if (!userRepository.existsById(userId)) {
			return ResponseEntity.notFound().build();
		}

		userService.deleteUser(userId);

		return ResponseEntity.noContent().build();
	}

}
