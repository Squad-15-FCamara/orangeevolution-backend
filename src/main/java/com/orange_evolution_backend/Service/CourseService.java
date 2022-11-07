package com.orange_evolution_backend.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.orange_evolution_backend.Entity.Course;
import com.orange_evolution_backend.Repository.CourseRepository;


import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class CourseService {
    CourseRepository courseRepository;

    public List<Course> findAllCourses(){
        return courseRepository.findAll();
    }
    
}
