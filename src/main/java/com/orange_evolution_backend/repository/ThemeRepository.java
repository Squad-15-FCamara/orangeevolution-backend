package com.orange_evolution_backend.repository;


import org.springframework.data.jpa.repository.JpaRepository;


import com.orange_evolution_backend.entity.Theme;

public interface ThemeRepository extends JpaRepository<Theme, Long> {
    public Theme findByName(String name);
}
