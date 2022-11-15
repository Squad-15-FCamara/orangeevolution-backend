// Aqui serve como Endpoints para metodos de contagem de dados dos Usuarios com os Conteudos,Trilhas e Temas

package com.orange_evolution_backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orange_evolution_backend.dto.StatisticDTO;
import com.orange_evolution_backend.service.StatisticAdminService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/statisticAdmin")
@AllArgsConstructor
@Api(description = "Statistic Admin HTTP methods", tags = "Statistics Admin")
public class StatisticAdminController {
    
    // Aqui está chamando os serviços que serão necessários para o funcionamento  da classe, seus contrutores estão sendo gerados de "@AllArgosConstructor"
    StatisticAdminService statisticAdminService;

    // Usa de StatisticAdminService para contar a quantidade de Usuários em andamento 
    // em um conteudo de acordo com o valor de Id conteudo passado.
    // Deve retornar o valor de usuarios com uma resposta HTtP 200.
    @ApiOperation(value = "Count Users doing a Course")
    @GetMapping("/coursesDoing/{idCourse}")
    public ResponseEntity<Long> countUserdoingCourseByIdCourse(@PathVariable Long idCourse) {
        return ResponseEntity.ok(statisticAdminService.counterUserDoing(idCourse));
    }

    // Usa de StatisticAdminService para contar a quantidade de Usuários que concluiram 
    // concluiram um conteudo de acordo com o valor de Id conteudo passado.
    // Deve retornar o valor de usuarios com uma resposta HTtP 200.
    @ApiOperation(value = "Count Users done a course")
    @GetMapping("/coursesDone/{idCourse}")
    public ResponseEntity<Long> countUserDoneCourseByIdCourse(@PathVariable Long idCourse) {
        return ResponseEntity.ok(statisticAdminService.counterUserDone(idCourse));
    }

    // Usa de StatisticAdminService para contar a quantidade de Usuários que nunca 
    // iniciaram um conteudo de acordo com o valor de Id conteudo passado.
    // Deve retornar o valor de usuarios com uma resposta HTtP 200.
    @ApiOperation(value = "Count Users didnt a course")
    @GetMapping("/coursesDidnt/{idCourse}")
    public ResponseEntity<Long> countUserDidntCourseByIdCourse(@PathVariable Long idCourse) {
        return ResponseEntity.ok(statisticAdminService.counterUserDidnt(idCourse));
    }

    // Usa StatisticAdminService para contar quantos Usuarios tem registrado
    // Deve retornar o valor de Usuários totais com uma resposta HTTP 200.
    @ApiOperation(value = "Count all users")
    @GetMapping("/counterUser")
    public ResponseEntity<Long> countAllUser(){
        return ResponseEntity.ok(statisticAdminService.counterAllUsers());
    }

    // Usa StatisticAdminService para contar quantos Conteudos tem registrado
    // Deve retornar o valor de Usuários totais com uma resposta HTTP 200.
    @ApiOperation(value = "Count all courses")
    @GetMapping("/counterCourses")
    public ResponseEntity<Long> countAllCoursers(){
        return ResponseEntity.ok(statisticAdminService.counterAllCourses());
    }

    // Usa de StatisticAdminService para contar a quantidade de Usuários que  
    // iniciaram uma trilha com o nome da Trilha passado.
    // Deve retornar o valor de usuarios com uma resposta HTtP 200.
    @ApiOperation(value = "Count Users doing a road")
    @GetMapping("/counter/user/doing/road/{name}")
    public ResponseEntity<Long> counterUserDoingRoad(@PathVariable String name){
        return ResponseEntity.ok(statisticAdminService.counterUserDoingRoad(name));
    }

    // Usa de StatisticAdminService para contar a quantidade de Usuários que  
    // finalizaram uma trilha com o nome da Trilha passada.
    // Deve retornar o valor de usuarios com uma resposta HTtP 200.
    @ApiOperation(value = "Count Users done a road")
    @GetMapping("/counter/user/done/road/{name}")
    public ResponseEntity<Long> counterUserDoneRoad(@PathVariable String name){
        return ResponseEntity.ok(statisticAdminService.counterUserDoneRoad(name));
    }

    // Usa de StatisticAdminService para contar a quantidade de Usuários que não
    // iniciaram  uma trilha com o nome da Trilha passada.
    // Deve retornar o valor de usuarios com uma resposta HTtP 200.
    @ApiOperation(value = "Counter Users didnt a road")
    @GetMapping("/counter/user/didnt/road/{name}")
    public ResponseEntity<Long> counterUserDidntRoad(@PathVariable String name){
        return ResponseEntity.ok(statisticAdminService.counterUserDidntRoad(name));
    }

    // Usa de StatisticAdminService para contar a quantidade de Usuários que  
    // iniciaram um Tema com o nome do tema  passado.
    // Deve retornar o valor de usuarios com uma resposta HTtP 200.
    @ApiOperation(value = "Counter Users doing a theme")
    @GetMapping("/counter/user/doing/theme/{name}")
    public ResponseEntity<Long> counterUserDoingTheme(@PathVariable String name){
        return ResponseEntity.ok(statisticAdminService.counterUserDoingTheme(name));
    }

    // Usa de StatisticAdminService para contar a quantidade de Usuários que  
    // finalizaram um Tema com o nome do tema  passado.
    // Deve retornar o valor de usuarios com uma resposta HTtP 200.
    @ApiOperation(value = "Counter Users done a theme")
    @GetMapping("/counter/user/done/theme/{name}")
    public ResponseEntity<Long> counterUserDoneTheme(@PathVariable String name){
        return ResponseEntity.ok(statisticAdminService.counterUserDoneTheme(name));
    }

    // Usa de StatisticAdminService para contar a quantidade de Usuários que não
    // iniciaram um Tema com o nome do tema  passado.
    // Deve retornar o valor de usuarios com uma resposta HTtP 200.
    @ApiOperation(value = "Counter Users didnt a theme")
    @GetMapping("/counter/user/didnt/theme/{name}")
    public ResponseEntity<Long> counterUsersDidntTheme(@PathVariable String name){
        return ResponseEntity.ok(statisticAdminService.counterUserDidntTheme(name));
    }

    // Usa de StatisticAdminService para contar a quantidade de Usuários que iniciaram,  
    // finalizaram e não iniciaram um Tema com o nome da trilha passada.
    // Deve retornar uma Lista de StatisticDTO com todos os temas e uma resposta HTtP 200.
    @ApiOperation(value = "Statics Themes to user by Road")
    @GetMapping("/counter/statitcs/roads/{id}")
    public ResponseEntity<List<StatisticDTO>> statistTheme(@PathVariable String id){
        return ResponseEntity.ok(statisticAdminService.statisticThemeByRoad(id));
    }

    // Usa de StatisticAdminService para contar a quantidade de Usuários que iniciaram,  
    // finalizaram e não iniciaram um Conteudo com o nome do Tema passado.
    // Deve retornar uma Lista de StatisticDTO com todos os conteúdos e uma resposta HTtP 200.
    @ApiOperation(value = "Statics Courses to user by Theme")
    @GetMapping("/counter/statistic/themes/{id}")
    public ResponseEntity<List<StatisticDTO>> statisticCourseByTeme(@PathVariable String id){
        return ResponseEntity.ok(statisticAdminService.statisticCourseBytheme(id));
    }

    // Usa de StatisticAdminService para contar a quantidade de Usuários que iniciaram,  
    // finalizaram e não iniciaram um Conteudo após uma busca de Conteudos que tenha o titulo passado.
    // Deve retornar uma Lista de StatisticDTO com todos os conteúdos e uma resposta HTtP 200.
    @ApiOperation(value = "Fetch a statistic course by title")
    @GetMapping("/counter/statistic/titles/{title}")
    public ResponseEntity<List<StatisticDTO>> getCoursesByTitle(@PathVariable String title){
        return ResponseEntity.ok(statisticAdminService.findCourseByTitle(title));
    }




}
