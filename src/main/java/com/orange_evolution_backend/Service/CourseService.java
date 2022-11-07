package com.orange_evolution_backend.Service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.orange_evolution_backend.Entity.Course;
import com.orange_evolution_backend.Repository.CourseRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CourseService {
    CourseRepository courseRepository;

    public List<Course> findAllCourses() {
        return courseRepository.findAll();
    }

    public Course findCourseByID(Long idCourse) {
        return courseRepository.findById(idCourse)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Este material não existe"));
    }

    public Course saveCourse(Course course) {
        courseRepository.save(course);
        return course;
    }
}
