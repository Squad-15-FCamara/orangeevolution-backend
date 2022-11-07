package com.orange_evolution_backend.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orange_evolution_backend.Entity.Course;
import com.orange_evolution_backend.Service.CourseService;

import lombok.AllArgsConstructor;

@CrossOrigin(origins = "*")
@RequestMapping("/courses")
@RestController
@AllArgsConstructor
public class CourseController {
    private CourseService courseService;

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses(){
        return ResponseEntity.ok(courseService.findAllCourses());
    }
    @GetMapping("/{idCourse}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long idCourse){
        return ResponseEntity.ok(courseService.findCourseByID(idCourse));
    }

    @GetMapping("/{time}")
    public ResponseEntity<List<Course>> getCoursesByTime(@PathVariable Long time){
        return ResponseEntity.ok(courseService.findCourseByTime(time));
    }

    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody Course course){
        return new ResponseEntity<Course>(courseService.saveCourse(course), HttpStatus.CREATED);
    } 

}
