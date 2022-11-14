package com.orange_evolution_backend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.orange_evolution_backend.repository.CourseRepository;
import com.orange_evolution_backend.repository.RoadRepoistory;
import com.orange_evolution_backend.repository.ThemeRepository;
import com.orange_evolution_backend.repository.UserRepository;
import com.orange_evolution_backend.dto.StatisticDTO;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StatisticAdminService {
    CourseRepository courseRepository;
    UserRepository userRepository;
    RoadRepoistory roadRepoistory;
    ThemeRepository themeRepository;
    AdminService adminService;
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
        return (long) themeRepository.findByName(theme).getUserDoingTheme().size();
    }

    public Long counterUserDoneTheme(String theme) {
        return (long) themeRepository.findByName(theme).getUserDoneTheme().size();
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
    

    public List<StatisticDTO> statisticThemeByRoad(String road){
        List<StatisticDTO> statistic = new ArrayList<StatisticDTO>();
        roadRepoistory.findByName(road).getThemes().stream().forEach(theme -> {
            String name = theme.getName();
            Long doing = counterUserDoingTheme(name);
            Long done = counterUserDoneTheme(name);
            Long didnt = counterUserDidntTheme(name);
            StatisticDTO save = new StatisticDTO(name,doing,done,didnt);
            statistic.add(save);
        });

        
        return statistic;
    }

    public List<StatisticDTO> statisticCourseBytheme(String theme){
        List<StatisticDTO> statistic = new ArrayList<StatisticDTO>();
        themeRepository.findByName(theme).getCourses().forEach(course ->{
            String name = course.getTitle();
            Long doing = counterUserDoing(course.getId());
            Long done = counterUserDone(course.getId());
            Long didnt = counterUserDidnt(course.getId());
            StatisticDTO save = new StatisticDTO(name, doing, done, didnt);
            statistic.add(save);
        });
        return statistic;
    }
}
