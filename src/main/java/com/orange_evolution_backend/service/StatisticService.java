// Este Servico serve para metodos de estatistica e atualizações para Conteudos "Favoritados", "em andamento" e "Finalizados" que sejam relacionados aos Conteudos, Usuarios.
package com.orange_evolution_backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.orange_evolution_backend.entity.Course;
import com.orange_evolution_backend.entity.User;
import com.orange_evolution_backend.exception.CourseNotFoundException;
import com.orange_evolution_backend.exception.ValidationException;
import com.orange_evolution_backend.repository.CourseRepository;
import com.orange_evolution_backend.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StatisticService {

    // Aqui está chamando os serviços que serão necessários para o funcionamento  da classe, seus contrutores estão sendo gerados de "@AllArgosConstructor"
    CourseRepository courseRepository;
    UserRepository userRepository;
    ValidationException validationException;
    AdminService adminService;


    // Adicionar um Conteudo aos Favoritos de um Usuario.
    // Recebe o Id do Usuario e o Id do curso como parametro.
    public Course favoriteCourse(Long idUser, Long idCourse) {
        Optional<User> userOpt = userRepository.findById(idUser);
        Optional<Course> courseOpt = courseRepository.findById(idCourse);

        if (userOpt.isPresent() && courseOpt.isPresent()) {
            Course course = courseOpt.get();
            if(userOpt.get().getCourses().contains(course)){
            throw new CourseNotFoundException("This course is already  favorited ");}
            course.getUsers().add(userOpt.get());
            return courseRepository.save(course);

        }

        throw new CourseNotFoundException("This course cant be favorited" );
    }

    // Procura os Favoritos de um Usuario.
    // Rececbe o Id do Usuario como parametro.
    public List<Course> findFavoritesCoursesByIdUser(Long idUser) {
        User user = userRepository.findById(idUser).get();
        List<Course> favoritCourses = (List<Course>) user.getCourses();

        return favoritCourses;
    }

    // Deleta um Favorito de um Usuario.
    // Recebe o Id do Usuario e o Id do Conteudo como parametro.
    public void deleteFavoriteCourse(Long idUser, Long idCourse) {
        User user = userRepository.findById(idUser).get();
        Course course = courseRepository.findById(idCourse).get();
        course.getUsers().remove(user);
        user.getCourses().remove(course);
        courseRepository.save(course);
        userRepository.save(user);
    }

    // Deleta todos os Favoritos de um Usuario.
    // Recebe o Id do usuario como parametro.
    public User deleteAllFavoriteCourse(Long idUser) {
        User user = userRepository.findById(idUser).get();
        user.getCourses().forEach(course -> {
            if (course.getUsers().contains(user)) {
                course.getUsers().remove(user);
                courseRepository.save(course);
            }
        });
        user.getCourses().removeAll(user.getCourses());
        return userRepository.save(user);
    }

    // Adicionar um Conteudo em "Andamento".
    // Recebe o Id do Usuario e o Id do Conteudo como parametro.
    public Course doingCourse(Long idUser, Long idCourse) {
        Optional<User> userOpt = userRepository.findById(idUser);
        Optional<Course> courseOpt = courseRepository.findById(idCourse);
        if (userOpt.isPresent() && courseOpt.isPresent()) {
            Course course = courseOpt.get();
            if(userOpt.get().getCourseDoing().contains(course)){
                throw new CourseNotFoundException("This course is already  doing ");}
            adminService.roadDoing(course,userOpt.get());
            adminService.themeDoing(course, userOpt.get());
            course.getUserDoing().add(userOpt.get());
            return courseRepository.save(course);
        }
        throw new CourseNotFoundException("This course cant be find");
    }

    // Deleta um conteudo em "Andamento".
    // Recebe o Id do Usuario e o Id do Conteudo como parametro.
    public void deleteDoingCourse(Long idUser, Long idCourse) {
        User user = userRepository.findById(idUser).get();
        Course course = courseRepository.findById(idCourse).get();
        course.getUserDoing().remove(user);
        user.getCourseDoing().remove(course);
        courseRepository.save(course);
        userRepository.save(user);
    }

    // Busca todos os conteudos em Andamento de um Usuario.
    // Recebe o Id do Usuario como parametro.
    public List<Course> findDoingCoursesByIdUser(Long idUser) {
        User user = userRepository.findById(idUser).get();
        List<Course> doingCourses = (List<Course>) user.getCourseDoing();

        return doingCourses;
    }

    // Salva um Conteudo em Finalizado e remove o mesmo conteudo de "Andamento".
    // Recebe id Usuario e Id conteudo como parametro.
    public Course doneCourse(Long idUser, Long idCourse) {
        Optional<User> userOpt = userRepository.findById(idUser);
        Optional<Course> courseOpt = courseRepository.findById(idCourse);
        if (userOpt.isPresent() && courseOpt.isPresent()) {
            Course course = courseOpt.get();
            if(userOpt.get().getCourseDone().contains(course)){
                throw new CourseNotFoundException("This course is already  done ");}
                course.getUserDone().add(userOpt.get());
                course.getUserDoing().remove(userOpt.get());
                adminService.themeDone(course, userOpt.get());
                adminService.roadDone(course, userOpt.get());
                return courseRepository.save(course);
            
            
        }
        throw new CourseNotFoundException("This course cant be find");
    }

    // Busca todos os Conteudos Finalizados de um Usuario.
    // Recebe o Id Usuario como parametro.
    public List<Course> findDoneCourseByIdUser(Long idUser) {
        User user = userRepository.findById(idUser).get();
        List<Course> doneCourses = (List<Course>) user.getCourseDone();
        return doneCourses;
    }

    // Deleta um Conteudo de Finalizado de um Usuario.
    // Recebe o Id usuario e Id Conteudo como parametro.
    public void deleteDoneCourse(Long idUser, Long idCourse) {
        User user = userRepository.findById(idUser).get();
        Course course = courseRepository.findById(idCourse).get();
        course.getUserDone().remove(user);
        user.getCourseDone().remove(course);
        courseRepository.save(course);
        userRepository.save(user);
    }

}
