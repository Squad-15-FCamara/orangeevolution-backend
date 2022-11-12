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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orange_evolution_backend.dto.CourseDTO;
import com.orange_evolution_backend.dto.CourseStingDTO;
import com.orange_evolution_backend.dto.UserDTO;
import com.orange_evolution_backend.entity.Course;
import com.orange_evolution_backend.entity.User;
import com.orange_evolution_backend.service.AdminService;
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
    ModelMapper modelMapper;
    AdminService adminService;



    @ApiOperation(value = "Add a favorite course")
    @GetMapping("/favorites/{userId}/{courseId}")
    public ResponseEntity<CourseStingDTO> addFavoriteCourse(@PathVariable Long userId, @PathVariable Long courseId) {
        Course returnCourse = statisticService.favoriteCourse(userId, courseId);
        return ResponseEntity.ok(convertCourseStringDTO(returnCourse));
    }

    @ApiOperation(value = "Fetch favorites courses by idUser")
    @GetMapping("/favorites/courses/{userId}")
    public ResponseEntity<List<CourseStingDTO>> findFavoritesCoursesByIdUser(@PathVariable Long userId){
        List<Course> returnCourses = statisticService.findFavoritesCoursesByIdUser(userId);
        return ResponseEntity.ok(converCoursesStringToListDTO(returnCourses));
    }

    @ApiOperation(value = "Add a Doing course")
    @GetMapping("/doing/{userId}/{courseId}")
    public ResponseEntity<CourseStingDTO> addDoingCourse(@PathVariable Long userId, @PathVariable Long courseId){
        Course returnCourse = statisticService.doingCourse(userId, courseId);
        return ResponseEntity.ok(convertCourseStringDTO(returnCourse));
    }

    @ApiOperation(value = "Fetch doing courses by idUser")
    @GetMapping("/doing/courses/{userId}")
    public ResponseEntity<List<CourseStingDTO>> findDoingCoursesByIdUser(@PathVariable Long userId){
        List<Course> returnCourse = statisticService.findDoingCoursesByIdUser(userId);
        return ResponseEntity.ok(converCoursesStringToListDTO(returnCourse));
    }

    @ApiOperation(value = "Add a Done course")
    @GetMapping("/done/{userId}/{courseId}")
    public ResponseEntity<CourseStingDTO> addDoneCourse(@PathVariable Long userId, @PathVariable Long courseId){
        Course returnCourse = statisticService.doneCourse(userId, courseId);
        return ResponseEntity.ok(convertCourseStringDTO(returnCourse));
    }

    @ApiOperation(value = "Fetch done courses by idUser")
    @GetMapping("/done/courses/{userId}")
    public ResponseEntity<List<CourseStingDTO>> findDoneCoursesByIdUser(@PathVariable Long userId){
        List<Course> returnCourses = statisticService.findDoneCourseByIdUser(userId);
        return ResponseEntity.ok(converCoursesStringToListDTO(returnCourses));
    }

    @ApiOperation(value = "Remove a user's favorite by idUser and IdCourse")
    @DeleteMapping("/delete/{idUser}/{idCourse}")
    public ResponseEntity<Void> removeFavoriteCourse(@PathVariable Long idUser, @PathVariable Long idCourse){
        statisticService.deleteFavoriteCourse(idUser, idCourse);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @ApiOperation(value = "remove a all user's favorite courses")
    @DeleteMapping("/delete/{idUser}")
    public ResponseEntity<UserDTO> removeAllFavoritesCourses (@PathVariable Long idUser){
        User user = statisticService.deleteAllFavoriteCourse(idUser);
        return new ResponseEntity<UserDTO>(convertUserToDTO(user),HttpStatus.ACCEPTED);
    }

    public List<CourseDTO> converCoursesToListDTO(List<Course> courses){
        List<CourseDTO> returnCoursesDTO = new ArrayList<>();
        courses.forEach(course ->{
            returnCoursesDTO.add(modelMapper.map(course, CourseDTO.class));
        });
        return returnCoursesDTO;
    }

    public CourseDTO convertCourseToDTO(Course course){
        return modelMapper.map(course, CourseDTO.class);
    }
    public Course converCourseToEntity(CourseDTO courseDTO){
        return modelMapper.map(courseDTO, Course.class);
    }

    public List<CourseStingDTO> converCoursesStringToListDTO(List<Course> courses){
        List<CourseStingDTO> returnCoursesDTO = new ArrayList<>();
        courses.forEach(course ->{
            CourseStingDTO courseStingDTO = modelMapper.map(course, CourseStingDTO.class);
            CourseDTO courseDTO = modelMapper.map(course,CourseDTO.class);
            courseStingDTO.setIdRoad(adminService.nameRoad(courseDTO.getIdRoad()));
            courseStingDTO.setIdTheme(adminService.nameTheme(courseDTO.getIdTheme()));
            courseStingDTO.setIdType(adminService.nameType(courseDTO.getIdType()));
            returnCoursesDTO.add(courseStingDTO);
        });
        return returnCoursesDTO;
    }

    public CourseStingDTO convertCourseStringDTO(Course course){
        CourseStingDTO courseStingDTO = modelMapper.map(course, CourseStingDTO.class);
        CourseDTO courseDTO = modelMapper.map(course,CourseDTO.class);
        courseStingDTO.setIdRoad(adminService.nameRoad(courseDTO.getIdRoad()));
        courseStingDTO.setIdTheme(adminService.nameTheme(courseDTO.getIdTheme()));
        courseStingDTO.setIdType(adminService.nameType(courseDTO.getIdType()));
        return courseStingDTO;
    }

    public UserDTO convertUserToDTO(User user){
        return modelMapper.map(user, UserDTO.class);
    }
    public User converUserToEntity(UserDTO userDTO){
        return modelMapper.map(userDTO, User.class);
    }
}