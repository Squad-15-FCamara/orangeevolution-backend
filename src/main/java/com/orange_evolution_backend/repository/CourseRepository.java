package com.orange_evolution_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orange_evolution_backend.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
    public List<Course> findByRoad(String road);
    public List<Course> findByTheme(String theme);
    public List<Course> findByauthor(String author);
    public List<Course> findByType(String type);

}