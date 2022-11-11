package com.orange_evolution_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orange_evolution_backend.entity.Type;

public interface TypeRepository extends JpaRepository<Type,Long> {
    public Type findByName(String name);
}
