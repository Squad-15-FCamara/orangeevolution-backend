package com.orange_evolution_backend.service;

import org.springframework.stereotype.Service;

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
        return roadRepoistory.findById(idTheme).get().getName();
    }

    public String nameType(Long idType){
        return roadRepoistory.findById(idType).get().getName();
    }

}
