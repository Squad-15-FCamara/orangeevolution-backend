package com.orange_evolution_backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orange_evolution_backend.entity.Course;
import com.orange_evolution_backend.repository.CourseRepository;
import com.orange_evolution_backend.service.CourseService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

@CrossOrigin(origins = "*")
@RequestMapping("/courses")
@RestController
@AllArgsConstructor
@Api(value = "Course HTTP methods")
public class CourseController {
	
    private CourseService courseService;
    private CourseRepository courseRepository;
    
    @ApiOperation(value = "Fetch all courses")
    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        return ResponseEntity.ok(courseService.findAllCourses());
    }
    
    @ApiOperation(value = "Fetch a course by ID")
    @GetMapping("/{idCourse}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long idCourse) {
        return ResponseEntity.ok(courseService.findCourseByID(idCourse));
    }
    
    @ApiOperation(value = "Fetch a course by time atribute")
    @GetMapping("/{time}")
    public ResponseEntity<List<Course>> getCoursesByTime(@PathVariable Long time) {
        return ResponseEntity.ok(courseService.findCourseByTime(time));
    }
    
    @ApiOperation(value = "Fetch a course by tag")
    @GetMapping("/{tag}")
    public ResponseEntity<List<Course>> getCoursesByTag(@PathVariable String tag) {
        return ResponseEntity.ok(courseService.findCourseByTag(tag));
    }
    
    @ApiOperation(value = "Create a course")
    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        return new ResponseEntity<Course>(courseService.saveCourse(course), HttpStatus.CREATED);
    }
    
    @ApiOperation(value = "Delete a course")
    @DeleteMapping("/{courseId}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long courseId) {
		if (!courseRepository.existsById(courseId)) {
			return ResponseEntity.notFound().build();
		}

		courseService.deleteCourse(courseId);

		return ResponseEntity.noContent().build();
	}

}
