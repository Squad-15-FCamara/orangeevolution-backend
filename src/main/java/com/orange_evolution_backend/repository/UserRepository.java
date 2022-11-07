package com.orange_evolution_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orange_evolution_backend.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
