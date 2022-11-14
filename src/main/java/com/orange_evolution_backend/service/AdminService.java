package com.orange_evolution_backend.service;

import java.util.ArrayList;

import java.util.List;

import org.springframework.stereotype.Service;

import com.orange_evolution_backend.entity.Course;
import com.orange_evolution_backend.entity.Road;
import com.orange_evolution_backend.entity.Theme;
import com.orange_evolution_backend.entity.Type;
import com.orange_evolution_backend.entity.User;
import com.orange_evolution_backend.exception.ValidationException;
import com.orange_evolution_backend.repository.RoadRepoistory;
import com.orange_evolution_backend.repository.ThemeRepository;
import com.orange_evolution_backend.repository.TypeRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AdminService {
    RoadRepoistory roadRepoistory;
    ThemeRepository themeRepository;
    TypeRepository typeRepository;
    ValidationException validationException;

    public Road saveRoad(Road road) {
        return roadRepoistory.save(road);
    }

    public Theme saveTheme(Theme theme) {
        return themeRepository.save(theme);
    }

    public Type saveType(Type type) {
        return typeRepository.save(type);
    }

    public String nameRoad(Long idRoad) {
        return roadRepoistory.findById(idRoad).get().getName();
    }

    public String nameTheme(Long idTheme) {
        return themeRepository.findById(idTheme).get().getName();
    }

    public String nameType(Long idType) {
        return typeRepository.findById(idType).get().getName();
    }

    public List<Road> findAllRoads() {
        return roadRepoistory.findAll();
    }

    public List<Theme> findAllThemes() {
        return themeRepository.findAll();
    }

    public List<Type> findAllTypes() {
        return typeRepository.findAll();
    }

    public List<Course> findAllCoursesByRoad(String road) {
        return (List<Course>) roadRepoistory.findByName(road).getCourses();
    }

    public List<Course> findAllCoursesByTheme(String theme) {
        return (List<Course>) themeRepository.findByName(theme).getCourses();
    }

    public List<Course> findAllCoursesByType(String type) {
        return (List<Course>) typeRepository.findByName(type).getCourses();
    }

    public List<String> findListNameRoad() {
        List<String> nameList = new ArrayList<>();
        findAllRoads().forEach(road -> {
            nameList.add(road.getName());
        });
        return nameList;
    }

    public List<String> findListNameTheme() {
        List<String> nameList = new ArrayList<>();
        findAllThemes().forEach(theme -> {
            nameList.add(theme.getName());
        });

        return nameList;
    }

    public List<String> findListNameType() {
        List<String> nameList = new ArrayList<>();
        findAllTypes().forEach(type -> {
            nameList.add(type.getName());
        });
        return nameList;

    }

    public void themeDoing(Course course, User user){
        Theme theme = course.getTheme();
        if(!theme.getUserDoingTheme().contains(user)){
            theme.getUserDoingTheme().add(user);
            themeRepository.save(theme);
        }
    }

    public void roadDoing(Course course, User user){
        Road road = course.getRoad();
        if(!road.getUserDoingRoad().contains(user)){
            road.getUserDoingRoad().add(user);
           
        }
    }

    public void themeDone(Course course, User user){
        Theme theme = course.getTheme();
        List<Course> courses = new ArrayList<>();
        theme.getCourses().forEach(courseL ->{
            if(courseL.getUserDoing().contains(user)){
               courses.add(courseL);
            }
        });
        if(courses.isEmpty()){
            theme.getUserDoingTheme().remove(user);
            theme.getUserDoneTheme().add(user);
            themeRepository.save(theme);
        }
    }

    public void roadDone(Course course, User user){
        Road road =  course.getRoad();
        List<Theme> themes = new ArrayList<>();
        road.getThemes().forEach(theme ->{
            if(theme.getUserDoingTheme().contains(user)){
                themes.add(theme);
            }
        });
        if(themes.isEmpty()){
            road.getUserDoingRoad().remove(user);
            road.getUserDoneRoad().add(user);
            roadRepoistory.save(road);
        }
    }

}
