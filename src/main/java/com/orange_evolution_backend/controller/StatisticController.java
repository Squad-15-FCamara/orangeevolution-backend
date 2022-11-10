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

import com.orange_evolution_backend.dto.ConvertDTO;
import com.orange_evolution_backend.dto.CourseDTO;
import com.orange_evolution_backend.entity.Course;
import com.orange_evolution_backend.service.StatisticService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

@CrossOrigin(origins = "*")
@RequestMapping("/statistics")
@AllArgsConstructor
@RestController
@Api(description = "Statistic HTTP methods", tags = "Statistics")
public class StatisticController {

    StatisticService statisticService;
    ConvertDTO convertDTO;


    @ApiOperation(value = "add a favorite course")
    @GetMapping("/favorites/{userId}/{courseId}")
    public ResponseEntity<CourseDTO> addFavoriteCourse(@PathVariable Long userId, @PathVariable Long courseId) {
        Course returnCourse = statisticService.favoriteCourse(userId, courseId);
        return ResponseEntity.ok(convertDTO.convertCourseToDTO(returnCourse));
    }

    @ApiOperation(value = "Fetch favorites courses by idUser")
    @GetMapping("/favorites/courses/{userId}")
    public ResponseEntity<List<CourseDTO>> findFavoritesCoursesByIdUser(@PathVariable Long userId){
        List<Course> returnCourses = statisticService.findFavoritesCoursesByIdUser(userId);
        return ResponseEntity.ok(convertDTO.converCoursesToListDTO(returnCourses));
    }

    @ApiOperation(value = "add a Doing course")
    @GetMapping("/doing/{userId}/{courseId}")
    public ResponseEntity<CourseDTO> addDoingCourse(@PathVariable Long userId, @PathVariable Long courseId){
        Course returnCourse = statisticService.doingCourse(userId, courseId);
        return ResponseEntity.ok(convertDTO.convertCourseToDTO(returnCourse));
    }

    @ApiOperation(value = "Fetch doing courses by idUser")
    @GetMapping("/doing/courses/{userId}")
    public ResponseEntity<List<CourseDTO>> findDoingCoursesByIdUser(@PathVariable Long userId){
        List<Course> returnCourse = statisticService.findDoingCoursesByIdUser(userId);
        return ResponseEntity.ok(convertDTO.converCoursesToListDTO(returnCourse));
    }

    @ApiOperation(value = "add a Done course")
    @GetMapping("/done/{userId}/{courseId}")
    public ResponseEntity<CourseDTO> addDoneCourse(@PathVariable Long userId, @PathVariable Long courseId){
        Course returnCourse = statisticService.doneCourse(userId, courseId);
        return ResponseEntity.ok(convertDTO.convertCourseToDTO(returnCourse));
    }

    @ApiOperation(value = "Fetch done courses by idUser")
    @GetMapping("/done/courses/{userId}")
    public ResponseEntity<List<CourseDTO>> findDoneCoursesByIdUser(@PathVariable Long userId){
        List<Course> returnCourses = statisticService.findDoneCourseByIdUser(userId);
        return ResponseEntity.ok(convertDTO.converCoursesToListDTO(returnCourses));
    }
}
