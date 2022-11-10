package com.orange_evolution_backend.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orange_evolution_backend.dto.CourseDTO;
import com.orange_evolution_backend.entity.Course;
import com.orange_evolution_backend.service.StatisticService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

@CrossOrigin(origins = "*")
@RequestMapping("statistics")
@AllArgsConstructor
@RestController
@Api(description = "Statistic HTTP methods", tags = "Statistics")
public class StatisticController {

    StatisticService statisticService;
    ModelMapper modelMapper;


    @ApiOperation(value = "add a favorite course")
    @GetMapping("/favorites/{userId}/{courseId}")
    public ResponseEntity<CourseDTO> addFavoriteCourse(@PathVariable Long userId, @PathVariable Long courseId) {
        Course returnCourse = statisticService.favoriteCourse(userId, courseId);
        return ResponseEntity.ok(convertToDTO(returnCourse));
    }

    @ApiOperation(value = "Fetch favorites courses by idUser")
    @GetMapping("/favorites/courses/{userId}")
    public ResponseEntity<List<CourseDTO>> findFavoritesCoursesByIdUser(@PathVariable Long userId){
        List<Course> returnCourses = statisticService.findFavoritesCoursesByIdUser(userId);
        return ResponseEntity.ok(converToListDTO(returnCourses));
    }

    @ApiOperation(value = "add a Doing course")
    @GetMapping("/doing/{userId}/{courseId}")
    public ResponseEntity<CourseDTO> addDoingCourse(@PathVariable Long userId, @PathVariable Long courseId){
        Course returnCourse = statisticService.doingCourse(userId, courseId);
        return ResponseEntity.ok(convertToDTO(returnCourse));
    }

    @ApiOperation(value = "Fetch doing courses by idUser")
    @GetMapping("/doing/courses/{userId}")
    public ResponseEntity<List<CourseDTO>> findDoingCoursesByIdUser(@PathVariable Long userId){
        List<Course> returnCourse = statisticService.findDoingCoursesByIdUser(userId);
        return ResponseEntity.ok(converToListDTO(returnCourse));
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
}
