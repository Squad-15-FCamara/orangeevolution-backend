// Este Servico serve para operacoes relacionadas com Conteudos e Usuarios.

package com.orange_evolution_backend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.orange_evolution_backend.entity.Course;
import com.orange_evolution_backend.exception.CourseNotFoundException;
import com.orange_evolution_backend.exception.ValidationException;
import com.orange_evolution_backend.repository.CourseRepository;
import com.orange_evolution_backend.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CourseService {

    // Aqui está chamando os serviços que serão necessários para o funcionamento  da classe, seus contrutores estão sendo gerados de "@AllArgosConstructor"
    CourseRepository courseRepository;
    UserRepository userRepository;
    ValidationException validationException;

    // Busca Todos os conteudos.
    public List<Course> findAllCourses() {
        return courseRepository.findAll();
    }

    // Busca um conteudo por ID.
    public Course findCourseByID(Long idCourse) {
        return courseRepository.findById(idCourse)
                .orElseThrow(() -> new CourseNotFoundException("Course " + idCourse + " not found"));
    }

    // Salva um novo conteudo.
    public Course saveCourse(Course course) {
        courseRepository.save(course);
        return course;
    }

    // Busca uma lista de Conteudos que estejam com um valor igual ou menor do passado.
    public List<Course> findCourseByTime(Long time) {
        List<Course> returnCourse = new ArrayList<>();
        findAllCourses().stream().forEach(course -> {

            if (course.getTime() <= time) {
                returnCourse.add(course);
            }
        });
        validationException.ValidationExceptionList(returnCourse);
        return returnCourse;
    }


    // Busca uma lista de Conteudos que seja da Tag passada.
    public List<Course> findCourseByTag(String tag) {
        List<Course> returnCourse = new ArrayList<>();
        findAllCourses().forEach(course -> {
            if (course.getTags().contains(tag)) {
                returnCourse.add(course);
            }
        });
        validationException.ValidationExceptionList(returnCourse,tag);
        return returnCourse;
    }


    // Busca uma lista de conteudos que tenha alguma parte do Titulo passado.
    public List<Course> findCourseByTitle(String title) {
        List<Course> returnCourse = new ArrayList<>();
        findAllCourses().forEach(course -> {
            String local = course.getTitle().toLowerCase();
            if (local.contains(title.toLowerCase())) {
                returnCourse.add(course);
            }
        });
        validationException.ValidationExceptionList(returnCourse, title);
        return returnCourse;
    }

    // Busca uma lista de Conteudos que seja do Autor passado.
    public List<Course> findCoursesByAuthor(String author) {
        List<Course> list = courseRepository.findByauthor(author);
        validationException.ValidationExceptionList(list, author);
        return list;
    }

    // Deleta um curso.
    public void deleteCourse(Long courseId) {
        courseRepository.deleteById(courseId);
    }

}
