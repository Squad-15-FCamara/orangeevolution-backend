// Este Servico serve para operacoes relacionadas com trilhas, temas e tipo.

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
    
    // Aqui está chamando os serviços que serão necessários para o funcionamento  da classe, seus contrutores estão sendo gerados de "@AllArgosConstructor"
    RoadRepoistory roadRepoistory;
    ThemeRepository themeRepository;
    TypeRepository typeRepository;
    ValidationException validationException;

    //Salva uma Trilha e retorna a Trilha.
    public Road saveRoad(Road road) {
        return roadRepoistory.save(road);
    }

    //Salva um tema e retorna o Tema
    public Theme saveTheme(Theme theme) {
        return themeRepository.save(theme);
    }

    //Salva um tipo e retorna o Tipo.
    public Type saveType(Type type) {
        return typeRepository.save(type);
    }

    //Busca o nome de uma Trilha pelo o Id da Trilha.
    public String nameRoad(Long idRoad) {
        return roadRepoistory.findById(idRoad).get().getName();
    }

    //Busca o nome de um Tema pelo o Id do Tema.
    public String nameTheme(Long idTheme) {
        return themeRepository.findById(idTheme).get().getName();
    }

    //Busca o nome de um Tipo pelo o Id do Tipo.
    public String nameType(Long idType) {
        return typeRepository.findById(idType).get().getName();
    }

    // Busca todas as Trilhas.
    public List<Road> findAllRoads() {
        return roadRepoistory.findAll();
    }

    // Busca todos os Temas.
    public List<Theme> findAllThemes() {
        return themeRepository.findAll();
    }

    // Busca todos os Tipos.
    public List<Type> findAllTypes() {
        return typeRepository.findAll();
    }

    // Busca todos os conteudo de uma Trilha pelo o nome da Trilha.
    public List<Course> findAllCoursesByRoad(String road) {
        return (List<Course>) roadRepoistory.findByName(road).getCourses();
    }

    // Busca todos os conteudo de um Tema pelo o nome do Tema.
    public List<Course> findAllCoursesByTheme(String theme) {
        return (List<Course>) themeRepository.findByName(theme).getCourses();
    }
    // Busca todos os conteudo de um Tipo pelo o nome do Tipo.
    public List<Course> findAllCoursesByType(String type) {
        return (List<Course>) typeRepository.findByName(type).getCourses();
    }

    // Busca todos os Temas de uma Trilha pelo o nome da trilha
    public List<Theme> findAllThemesByRoad(String road){
        return (List<Theme>) roadRepoistory.findByName(road).getThemes();
    }

    // Busca o nome de todas as trilhas.
    public List<String> findListNameRoad() {
        List<String> nameList = new ArrayList<>();
        findAllRoads().forEach(road -> {
            nameList.add(road.getName());
        });
        return nameList;
    }
    
    // Busca o nome de todos os temas.
    public List<String> findListNameTheme() {
        List<String> nameList = new ArrayList<>();
        findAllThemes().forEach(theme -> {
            nameList.add(theme.getName());
        });
        return nameList;
    }

    // Busca o nome de todos os tipos.
    public List<String> findListNameType() {
        List<String> nameList = new ArrayList<>();
        findAllTypes().forEach(type -> {
            nameList.add(type.getName());
        });
        return nameList;

    }

    // Salva um tema em "Fazendo" recebendo o conteudo e o usuario.
    public void themeDoing(Course course, User user){
        Theme theme = course.getTheme();
        if(!theme.getUserDoingTheme().contains(user)){
            theme.getUserDoingTheme().add(user);
            themeRepository.save(theme);   
        }
    }

    // Salva uma Trilha em "Fazendo" recebendo o conteudo e o usuario.
    public void roadDoing(Course course, User user){
        Road road = course.getRoad();
        if(!road.getUserDoingRoad().contains(user)){
            road.getUserDoingRoad().add(user);
            roadRepoistory.save(road);
        }
    }

    // Salva um Tema em "Feito" recebendo o conteudo e o usuario.
    public void themeDone(Course course, User user){
        Theme theme = course.getTheme();
        if(!user.getUserDoingTheme().contains(theme)){
            theme.getUserDoingTheme().remove(user);
            theme.getUserDoneTheme().add(user);
            themeRepository.save(theme);
        }
    }

    // Salva uma Trilha em "Feito" recebendo o conteudo e o usuario.
    public void roadDone(Course course, User user){
        Road road =  course.getRoad();
        if(!user.getUserDoingRoad().contains(road)){
            road.getUserDoingRoad().remove(user);
            road.getUserDoneRoad().add(user);
            roadRepoistory.save(road);
        }
    }


    // Atualiza um Tema recebendo o Tema e o Id do Tema.
    public Theme updaTheme(Theme themeP, Long nameTheme){
        Theme theme = themeRepository.findById(nameTheme).get();
        theme.setName(themeP.getName());
        theme.setRoadTheme(themeP.getRoadTheme());
        return themeRepository.save(theme);
    }

    // Busca uma Trilha pelo o nome.
    public Long findIdRoadByName(String name){
        return roadRepoistory.findByName(name).getId();
    }

    // Busca um Tema pelo o nome.
    public Long findIdThemeByName(String name){
        return themeRepository.findByName(name).getId();
    }

    // Busca um Tipo pelo o nome.
    public Long findIdTypeByName(String name){
        return typeRepository.findByName(name).getId();
    }

}
