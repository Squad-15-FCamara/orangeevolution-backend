package com.orange_evolution_backend.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orange_evolution_backend.Entity.Course;



public interface CourseRepository extends JpaRepository<Course,Long> {
    public List<Course> findByTime(Long time);
    public List<Course> findByTags(String tag);
}
