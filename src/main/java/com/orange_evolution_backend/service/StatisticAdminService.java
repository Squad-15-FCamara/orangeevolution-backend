package com.orange_evolution_backend.service;

import org.springframework.stereotype.Service;

import com.orange_evolution_backend.repository.CourseRepository;
import com.orange_evolution_backend.repository.RoadRepoistory;
import com.orange_evolution_backend.repository.ThemeRepository;
import com.orange_evolution_backend.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StatisticAdminService {
    CourseRepository courseRepository;
    UserRepository userRepository;
    RoadRepoistory roadRepoistory;
    ThemeRepository themeRepository;

    public Long counterUserDoing(Long idCourse) {
        return (long) courseRepository.findById(idCourse).get().getUserDoing().size();
    }

    public Long counterUserDone(Long idcourse) {
        return (long) courseRepository.findById(idcourse).get().getUserDone().size();
    }

    public Long counterUserDidnt(Long idCourse) {
        return (long) (userRepository.findAll().size() - (courseRepository.findById(idCourse).get().getUserDone().size()
                + courseRepository.findById(idCourse).get().getUserDoing().size()));
    }

    public Long counterAllUsers() {
        return (long) userRepository.findAll().size();
    }

    public Long counterAllCourses() {
        return (long) courseRepository.findAll().size();
    }

    public Long counterUserDoingRoad(String road) {
        return (long) roadRepoistory.findByName(road).getUserDoingRoad().size();
    }

    public Long counterUserDoneRoad(String road) {
        return (long) roadRepoistory.findByName(road).getUserDoneRoad().size();
    }

    public Long counterUserDoingTheme(String theme) {
        return (long) roadRepoistory.findByName(theme).getUserDoingRoad().size();
    }

    public Long counterUserDoneTheme(String theme) {
        return (long) roadRepoistory.findByName(theme).getUserDoneRoad().size();
    }

    public Long counterUserDidntRoad(String road) {
        return (long) (userRepository.findAll().size()
                - (roadRepoistory.findByName(road).getUserDoingRoad().size()
                        + roadRepoistory.findByName(road).getUserDoneRoad().size()));
    }

    public Long counterUserDidntTheme(String theme) {
        return (long) (userRepository.findAll().size()
                - (themeRepository.findByName(theme).getUserDoingTheme().size() +
                        themeRepository.findByName(theme).getUserDoneTheme().size()));
    }

}
