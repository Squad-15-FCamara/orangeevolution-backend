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
    
    
    public List<Course> findFavoritesCoursesByIdUser(Long idUser){
        User user = userRepository.findById(idUser).get();
        List<Course> favoritCourses = (List<Course>) user.getCourses() ;

        return favoritCourses;
    }

    public Course doingCourse(Long idUser, Long idCourse){
        Optional<User> userOpt = userRepository.findById(idUser);
        Optional<Course> courseOpt = courseRepository.findById(idCourse);

        if(userOpt.isPresent() && courseOpt.isPresent()){
            Course course = courseOpt.get();
            course.getUserDoing().add(userOpt.get());
            return courseRepository.save(course);
        }

        return null;
    }

    public List<Course> findDoingCoursesByIdUser(Long idUser){
        User user = userRepository.findById(idUser).get();
        List<Course> doingCourses = (List<Course>) user.getCourseDoing();

        return doingCourses;
    }

    public Course doneCourse(Long idUser, Long idCourse){
        Optional<User> userOpt = userRepository.findById(idUser);
        Optional<Course> courseOpt = courseRepository.findById(idCourse);
        if(userOpt.isPresent() && courseOpt.isPresent()){
            Course course = courseOpt.get();
            course.getUserDone().add(userOpt.get());
            return courseRepository.save(course);
        }
        return null;
    }

    public List<Course> findDoneCourseByIdUser(Long idUser){
        User user = userRepository.findById(idUser).get();
        List<Course> doneCourses = (List<Course>) user.getCourseDone();
        return doneCourses;
    }


}
