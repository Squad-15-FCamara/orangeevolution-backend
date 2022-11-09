package com.orange_evolution_backend.service;

import java.util.List;

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
    
    public List<Course> findFavoritesCoursesByIdUser(Long idUser, Long idcourse){
        User userFavorite = userRepository.findById(idcourse).get();
        List<Course> favoritCourses = (List<Course>) userFavorite.getCourses() ;

        return favoritCourses;
    }
}
