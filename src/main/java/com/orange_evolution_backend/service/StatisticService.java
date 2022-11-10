package com.orange_evolution_backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.orange_evolution_backend.entity.Course;
import com.orange_evolution_backend.entity.User;
import com.orange_evolution_backend.repository.CourseRepository;
import com.orange_evolution_backend.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StatisticService {
    CourseRepository courseRepository;
    UserRepository userRepository;


    public Course favoriteCourse(Long idUser, Long idCourse){
        Optional<User> userOpt = userRepository.findById(idUser);
        Optional<Course> courseOpt = courseRepository.findById(idCourse);

        if(userOpt.isPresent() && courseOpt.isPresent()){
            Course course = courseOpt.get();
            course.getUsers().add(userOpt.get());

            return courseRepository.save(course);
            
        }

        return null;
    }
    
    
    public List<Course> findFavoritesCoursesByIdUser(Long idUser, Long idcourse){
        User userFavorite = userRepository.findById(idcourse).get();
        List<Course> favoritCourses = (List<Course>) userFavorite.getCourses() ;

        return favoritCourses;
    }
}
