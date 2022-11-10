package com.orange_evolution_backend.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
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

import com.orange_evolution_backend.dto.CourseDTO;
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
@Api(description = "Course HTTP methods", tags = "Courses")
public class CourseController {

    private CourseService courseService;
    private CourseRepository courseRepository;
    private ModelMapper modelMapper;

    @ApiOperation(value = "Fetch all courses")
    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        return ResponseEntity.ok(courseService.findAllCourses());
    }

    @ApiOperation(value = "Fetch a course by ID")
    @GetMapping("/{idCourse}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable Long idCourse) {
        Course course = courseService.findCourseByID(idCourse);
        return ResponseEntity.ok(convertToDTO(course));
    }

    @ApiOperation(value = "Fetch a course by time atribute")
    @GetMapping("/times/{time}")
    public ResponseEntity<List<CourseDTO>> getCoursesByTime(@PathVariable Long time) {
        List<Course> courses = courseService.findCourseByTime(time);
        return ResponseEntity.ok(converToListDTO(courses));
    }

    @ApiOperation(value = "Fetch a course by tag")
    @GetMapping("/tags/{tag}")
    public ResponseEntity<List<CourseDTO>> getCoursesByTag(@PathVariable String tag) {
        List<Course> courses = courseService.findCourseByTag(tag);
        return ResponseEntity.ok(converToListDTO(courses));
    }

    @ApiOperation(value = "Fetch a course by road")
    @GetMapping("/roads/{road}")
    public ResponseEntity<List<CourseDTO>> getCoursesByRoad(@PathVariable String road) {
        List<Course> courses = courseService.findCourseByRoad(road);
        return ResponseEntity.ok(converToListDTO(courses));
    }

    @ApiOperation(value = "Fetch a course by theme")
    @GetMapping("/themes/{theme}")
    public ResponseEntity<List<CourseDTO>> getCoursesByTheme(@PathVariable String theme) {
        List<Course> courses = courseService.findCourseByTheme(theme);
        return ResponseEntity.ok(converToListDTO(courses));
    }

    @ApiOperation(value = "Fetch a course by author")
    @GetMapping("/authors/{author}")
    public ResponseEntity<List<CourseDTO>> getCoursesByAuthor(@PathVariable String author) {
        List<Course> courses = courseService.findCoursesByAuthor(author);
        return ResponseEntity.ok(converToListDTO(courses));
    }

    @ApiOperation(value = "Fetch a course by type")
    @GetMapping("/types/{type}")
    public ResponseEntity<List<CourseDTO>> getCoursesByType(@PathVariable String type) {
        List<Course> courses = courseService.findCoursesByType(type);
        return ResponseEntity.ok(converToListDTO(courses));
    }

    @ApiOperation(value = "Create a course")
    @PostMapping
    public ResponseEntity<CourseDTO> createCourse(@RequestBody CourseDTO courseDTO) {
        Course course = converToEntity(courseDTO);
        Course saved = courseService.saveCourse(course);
        return new ResponseEntity<CourseDTO>(convertToDTO(saved), HttpStatus.CREATED);
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

    private List<CourseDTO> converToListDTO(List<Course> courses){
        List<CourseDTO> returnCoursesDTO = new ArrayList<>();
        courses.forEach(course ->{
            returnCoursesDTO.add(modelMapper.map(course, CourseDTO.class));
        });
        return returnCoursesDTO;
    }

    private CourseDTO convertToDTO(Course course){
        return modelMapper.map(course, CourseDTO.class);
    }
    private Course converToEntity(CourseDTO courseDTO){
        return modelMapper.map(courseDTO, Course.class);
    }

}
