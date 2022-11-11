package com.orange_evolution_backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.orange_evolution_backend.entity.Course;
import com.orange_evolution_backend.entity.Road;
import com.orange_evolution_backend.entity.Theme;
import com.orange_evolution_backend.entity.Type;
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


    public Road saveRoad(Road road){
        return roadRepoistory.save(road);
    }

    public Theme saveTheme(Theme theme){
        return themeRepository.save(theme);
    }

    public Type saveType(Type type){
        return typeRepository.save(type);
    }

    public String nameRoad(Long idRoad){
        return roadRepoistory.findById(idRoad).get().getName();
    }

    public String nameTheme(Long idTheme){
        return themeRepository.findById(idTheme).get().getName();
    }

    public String nameType(Long idType){
        return typeRepository.findById(idType).get().getName();
    }


    public List<Road> findAllRoads(){
        return roadRepoistory.findAll();
    }

    public List<Course> findAllCoursesByRoad(String road){
        return (List<Course>) roadRepoistory.findByName(road).getCourses();
    }

    public List<Course> findAllCoursesByTheme(String theme){
        return (List<Course>) themeRepository.findByName(theme).getCourses();
    }

    public List<Course> findAllCoursesByType(String type){
        return (List<Course>) typeRepository.findByName(type).getCourses();
    }

}
