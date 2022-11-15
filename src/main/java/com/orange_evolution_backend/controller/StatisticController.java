// Aqui serve como Endpoints para metodos de AdminService e StatisticService

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

    // Aqui está chamando os serviços que serão necessários para o funcionamento  da classe, seus contrutores estão sendo gerados de "@AllArgosConstructor"
    StatisticService statisticService;
    ModelMapper modelMapper;
    AdminService adminService;



    // Utiliza StatisticService e os convertores de DTO para adicionar um novo curso em Favoritos
    // recebendo os parametros de Id usuario e Id conteudo.
    // Deve retornar o Conteudo String DTO e uma resposta HTTP 200.  
    @ApiOperation(value = "Add a favorite course")
    @GetMapping("/favorites/{userId}/{courseId}")
    public ResponseEntity<CourseStingDTO> addFavoriteCourse(@PathVariable Long userId, @PathVariable Long courseId) {
        Course returnCourse = statisticService.favoriteCourse(userId, courseId);
        return ResponseEntity.ok(convertCourseStringDTO(returnCourse));
    }

    // Utiliza StatisticService e os convertores DTO para encontrar os conteudos Favoritados 
    // recebendo o paramentro de Id Usuario.
    // Reve retornar uma Lista de Conteudo String DTO e uma resposta HTTP 200.
    @ApiOperation(value = "Fetch favorites courses by idUser")
    @GetMapping("/favorites/courses/{userId}")
    public ResponseEntity<List<CourseStingDTO>> findFavoritesCoursesByIdUser(@PathVariable Long userId){
        List<Course> returnCourses = statisticService.findFavoritesCoursesByIdUser(userId);
        return ResponseEntity.ok(converCoursesStringToListDTO(returnCourses));
    }

    
    // Utiliza StatisticService e os convertores de DTO para adicionar um novo curso em andamento
    // recebendo os parametros de Id usuario e Id conteudo.
    // Deve retornar o Conteudo String DTO e uma resposta HTTP 200.
    @ApiOperation(value = "Add a Doing course")
    @GetMapping("/doing/{userId}/{courseId}")
    public ResponseEntity<CourseStingDTO> addDoingCourse(@PathVariable Long userId, @PathVariable Long courseId){
        Course returnCourse = statisticService.doingCourse(userId, courseId);
        return ResponseEntity.ok(convertCourseStringDTO(returnCourse));
    }

    // Utiliza StatisticService e os convertores DTO para encontrar os conteudos em andamento 
    // recebendo o paramentro de Id Usuario.
    // Reve retornar uma Lista de Conteudo String DTO e uma resposta HTTP 200.
    @ApiOperation(value = "Fetch doing courses by idUser")
    @GetMapping("/doing/courses/{userId}")
    public ResponseEntity<List<CourseStingDTO>> findDoingCoursesByIdUser(@PathVariable Long userId){
        List<Course> returnCourse = statisticService.findDoingCoursesByIdUser(userId);
        return ResponseEntity.ok(converCoursesStringToListDTO(returnCourse));
    }

    
    // Utiliza StatisticService e os convertores de DTO para adicionar um novo curso em feitos
    // recebendo os parametros de Id usuario e Id conteudo.
    // Deve retornar o Conteudo String DTO e uma resposta HTTP 200.
    @ApiOperation(value = "Add a Done course")
    @GetMapping("/done/{userId}/{courseId}")
    public ResponseEntity<CourseStingDTO> addDoneCourse(@PathVariable Long userId, @PathVariable Long courseId){
        Course returnCourse = statisticService.doneCourse(userId, courseId);
        return ResponseEntity.ok(convertCourseStringDTO(returnCourse));
    }

    // Utiliza StatisticService e os convertores DTO para encontrar os conteudos Favoritados 
    // recebendo o paramentro de Id Usuario.
    // Reve retornar uma Lista de Conteudo String DTO e uma resposta HTTP 200.
    @ApiOperation(value = "Fetch done courses by idUser")
    @GetMapping("/done/courses/{userId}")
    public ResponseEntity<List<CourseStingDTO>> findDoneCoursesByIdUser(@PathVariable Long userId){
        List<Course> returnCourses = statisticService.findDoneCourseByIdUser(userId);
        return ResponseEntity.ok(converCoursesStringToListDTO(returnCourses));
    }

    // Utiliza StatisticService e os convertores DTO para remover um conteudo Favoritado 
    // recebendo o paramentro de Id Usuario e Id Conteudo.
    // Reve retornar uma Lista de Conteudo String DTO e uma resposta HTTP 202.
    @ApiOperation(value = "Remove a user's favorite by idUser and IdCourse")
    @DeleteMapping("/delete//favortes/{idUser}/{idCourse}")
    public ResponseEntity<Void> removeFavoriteCourse(@PathVariable Long idUser, @PathVariable Long idCourse){
        statisticService.deleteFavoriteCourse(idUser, idCourse);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }


    // Utiliza StatisticService para remover todos os favoritos de 
    // um Usuario recebendo o parametro de Id User.
    // Deve retornar o Usuario DTO que solicitou e uma resposta HTTP 202.
    @ApiOperation(value = "remove a all user's favorite courses")
    @DeleteMapping("/delete/{idUser}")
    public ResponseEntity<UserDTO> removeAllFavoritesCourses (@PathVariable Long idUser){
        User user = statisticService.deleteAllFavoriteCourse(idUser);
        return new ResponseEntity<UserDTO>(convertUserToDTO(user),HttpStatus.ACCEPTED);
    }

    // Utiliza StatisticService e os convertores DTO para remover um conteudo em andamento 
    // recebendo o paramentro de Id Usuario e Id Conteudo.
    // Reve retornar uma resposta HTTP 202.
    @ApiOperation(value = "Remove a user's doing course by idUser and IdCourse")
    @DeleteMapping("/delete/doing/{idUser}/{idCourse}")
    public ResponseEntity<Void> removeDoingCourse(@PathVariable Long idUser, @PathVariable Long idCourse){
        statisticService.deleteDoingCourse(idUser, idCourse);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    // Utiliza StatisticService e os convertores DTO para remover um conteudo finalizado
    // recebendo o paramentro de Id Usuario e Id Conteudo.
    // Reve retornar uma resposta HTTP 202.
    @ApiOperation(value = "Remove a user's done course by idUser and IdCourse")
    @DeleteMapping("/delete/done/{idUser}/{idCourse}")
    public ResponseEntity<Void> removeDoneCourse(@PathVariable Long idUser, @PathVariable Long idCourse){
        statisticService.deleteDoneCourse(idUser, idCourse);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }


    // Usa o serviço de ModelMapper para converter uma Lista de Conteudos
    // em uma lista de Conteudos DTO.
    public List<CourseDTO> converCoursesToListDTO(List<Course> courses){
        List<CourseDTO> returnCoursesDTO = new ArrayList<>();
        courses.forEach(course ->{
            returnCoursesDTO.add(modelMapper.map(course, CourseDTO.class));
        });
        return returnCoursesDTO;
    }

    // Usa ModelMapper para converter um Conteudo em Conteudo DTO.
    public CourseDTO convertCourseToDTO(Course course){
        return modelMapper.map(course, CourseDTO.class);
    }

    // Usa ModelMapper para converter um Conteudo DTO em Conteudo.
    public Course converCourseToEntity(CourseDTO courseDTO){
        return modelMapper.map(courseDTO, Course.class);
    }

    // Usa ModelMapper para converter uma lista de Conteudos em Conteudos String DTO.
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

    // Usa ModelMapper para converter um conteudo em Conteudo String DTO.
    public CourseStingDTO convertCourseStringDTO(Course course){
        CourseStingDTO courseStingDTO = modelMapper.map(course, CourseStingDTO.class);
        CourseDTO courseDTO = modelMapper.map(course,CourseDTO.class);
        courseStingDTO.setIdRoad(adminService.nameRoad(courseDTO.getIdRoad()));
        courseStingDTO.setIdTheme(adminService.nameTheme(courseDTO.getIdTheme()));
        courseStingDTO.setIdType(adminService.nameType(courseDTO.getIdType()));
        return courseStingDTO;
    }

    // Usa ModelMapper para converter um Usuario em Usuario DTO.
    public UserDTO convertUserToDTO(User user){
        return modelMapper.map(user, UserDTO.class);
    }
    // Usa ModelMapper para converter um Usuario DTO em Usuario.
    public User converUserToEntity(UserDTO userDTO){
        return modelMapper.map(userDTO, User.class);
    }
}