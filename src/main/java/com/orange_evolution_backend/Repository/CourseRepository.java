package com.orange_evolution_backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orange_evolution_backend.Entity.Course;

public interface CourseRepository extends JpaRepository<Course,Long> {
    
}
