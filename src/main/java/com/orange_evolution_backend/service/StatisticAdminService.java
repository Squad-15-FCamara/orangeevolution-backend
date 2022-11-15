// Este Servico serve para produzir contagem de dados que sejam relacionados aos Conteudos, Usuarios, Trilhas, Temas.

package com.orange_evolution_backend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.orange_evolution_backend.repository.CourseRepository;
import com.orange_evolution_backend.repository.RoadRepoistory;
import com.orange_evolution_backend.repository.ThemeRepository;
import com.orange_evolution_backend.repository.UserRepository;
import com.orange_evolution_backend.dto.StatisticDTO;
import com.orange_evolution_backend.exception.ValidationException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StatisticAdminService {

    // Aqui está chamando os serviços que serão necessários para o funcionamento  da classe, seus contrutores estão sendo gerados de "@AllArgosConstructor"
    CourseRepository courseRepository;
    UserRepository userRepository;
    RoadRepoistory roadRepoistory;
    ThemeRepository themeRepository;
    AdminService adminService;
    ValidationException validationException;

    // Conta a quantiade de Usuarios estao realizando um Conteudo pelo ID Conteudo.
    public Long counterUserDoing(Long idCourse) {
        return (long) courseRepository.findById(idCourse).get().getUserDoing().size();
    }

    // Conta a quantiade de Usuarios que terminaram um Conteudo pelo ID Conteudo.
    public Long counterUserDone(Long idcourse) {
        return (long) courseRepository.findById(idcourse).get().getUserDone().size();
    }

    // Conta a quantiade de Usuarios nao começaram um Conteudo pelo ID Conteudo.
    public Long counterUserDidnt(Long idCourse) {
        Long counter = (long) (userRepository.findAll().size() - (courseRepository.findById(idCourse).get().getUserDone().size()
        + courseRepository.findById(idCourse).get().getUserDoing().size()));
        if(counter < 0){
            return (long) 0;
        }
        return counter; 
    }

    // Conta quantos  Usuarios tem no total.
    public Long counterAllUsers() {
        return (long) userRepository.findAll().size();
    }

    // Conta quantos Conteudos tem no total.
    public Long counterAllCourses() {
        return (long) courseRepository.findAll().size();
    }

    // Conta quantos Usuarios estao fazendo uma Trilha de acordo com o nome da Trilha passado.
    public Long counterUserDoingRoad(String road) {
        return (long) roadRepoistory.findByName(road).getUserDoingRoad().size();
    }

    // Conta quantos Usuarios terminaram uma Trilha de acordo com o nome da Trilha passado.
    public Long counterUserDoneRoad(String road) {
        return (long) roadRepoistory.findByName(road).getUserDoneRoad().size();
    }

    // Conta quantos Usuarios estao fazendo um Tema de acordo com o nome do Tema passado.
    public Long counterUserDoingTheme(String theme) {
        return (long) themeRepository.findByName(theme).getUserDoingTheme().size();
    }

    // Conta quantos Usuarios não começaram um Tema de acordo com o nome do Tema passado.
    public Long counterUserDoneTheme(String theme) {
        return (long) themeRepository.findByName(theme).getUserDoneTheme().size();
    }

    // Conta quantos Usuarios não começaram uma Trilha de acordo com o nome da Trilha passado.
    public Long counterUserDidntRoad(String road) {

        Long counter = (long) (userRepository.findAll().size()
        - (roadRepoistory.findByName(road).getUserDoingRoad().size()
                + roadRepoistory.findByName(road).getUserDoneRoad().size()));

        if(counter < 0) {return (long) 0;}
        return counter;
    }

    // Conta quantos Usuarios não começaram um Tema de acordo com o nome do Tema passado.
    public Long counterUserDidntTheme(String theme) {
        Long counter = (long) (userRepository.findAll().size()
        - (themeRepository.findByName(theme).getUserDoingTheme().size() +
                themeRepository.findByName(theme).getUserDoneTheme().size()));
        if(counter < 0){
            return (long) 0;
        }  return counter; 
    }
    
    // Retorna uma lista de StatisticDTO des descrevendo quantos Usuarios
    // começaram, terminaram e não começaram cada tema da Trilha que foi passada 
    // no parametro, no qual recebe o nome da Trilha.
    public List<StatisticDTO> statisticThemeByRoad(String road){
        List<StatisticDTO> statistic = new ArrayList<StatisticDTO>();
        roadRepoistory.findByName(road).getThemes().stream().forEach(theme -> {
            String name = theme.getName();
            Long doing = counterUserDoingTheme(name);
            Long done = counterUserDoneTheme(name);
            Long didnt = counterUserDidntTheme(name);
            String idRoad = theme.getRoadTheme().getName();
            StatisticDTO save = new StatisticDTO(name, idRoad, doing, done, didnt);
            statistic.add(save);
        });

        
        return statistic;
    }

    // Retorna uma lista de StatisticDTO des descrevendo quantos Usuarios
    // começaram, terminaram e não começaram cada Conteudo do Tema que foi passada 
    // no parametro, no qual recebe o nome do Tema.
    public List<StatisticDTO> statisticCourseBytheme(String theme){
        List<StatisticDTO> statistic = new ArrayList<StatisticDTO>();
        themeRepository.findByName(theme).getCourses().forEach(course ->{
            String name = course.getTitle();
            Long doing = counterUserDoing(course.getId());
            Long done = counterUserDone(course.getId());
            Long didnt = counterUserDidnt(course.getId());
            String idRoad = course.getRoad().getName();
            StatisticDTO save = new StatisticDTO(name,idRoad, doing, done, didnt);
            statistic.add(save);
        });
        return statistic;
    }


    // Retorna uma lista de StatisticDTO des descrevendo quantos Usuarios
    // começaram, terminaram e não começaram cada Conteudo de acordo com o  
    // titulo, no qual recebe o nome o Titulo de Conteudo como parametro.
    public List<StatisticDTO> findCourseByTitle(String title) {
        List<StatisticDTO> returnCourse = new ArrayList<>();
        courseRepository.findAll().forEach(course -> {
            String local = course.getTitle().toLowerCase();
            if (local.contains(title.toLowerCase())) {
                String name = course.getTitle();
                Long doing = counterUserDoing(course.getId());
                Long done = counterUserDone(course.getId());
                Long didnt = counterUserDidnt(course.getId());
                String idRoad = course.getRoad().getName();
                StatisticDTO save = new StatisticDTO(name, idRoad, doing, done, didnt);
                returnCourse.add(save);
            }
        });
        validationException.ValidationExceptionList(returnCourse, title);
        return returnCourse;
    }

    

    
}
