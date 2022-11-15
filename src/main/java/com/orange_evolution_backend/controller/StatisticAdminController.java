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

    StatisticAdminService statisticAdminService;

    @ApiOperation(value = "Count Users doing a Course")
    @GetMapping("/coursesDoing/{idCourse}")
    public ResponseEntity<Long> countUserdoingCourseByIdCourse(@PathVariable Long idCourse) {
        return ResponseEntity.ok(statisticAdminService.counterUserDoing(idCourse));
    }

    @ApiOperation(value = "Count Users done a course")
    @GetMapping("/coursesDone/{idCourse}")
    public ResponseEntity<Long> countUserDoneCourseByIdCourse(@PathVariable Long idCourse) {
        return ResponseEntity.ok(statisticAdminService.counterUserDone(idCourse));
    }

    @ApiOperation(value = "Count Users didnt a course")
    @GetMapping("/coursesDidnt/{idCourse}")
    public ResponseEntity<Long> countUserDidntCourseByIdCourse(@PathVariable Long idCourse) {
        return ResponseEntity.ok(statisticAdminService.counterUserDidnt(idCourse));
    }

    @ApiOperation(value = "Count all users")
    @GetMapping("/counterUser")
    public ResponseEntity<Long> countAllUser(){
        return ResponseEntity.ok(statisticAdminService.counterAllUsers());
    }

    @ApiOperation(value = "Count all courses")
    @GetMapping("/counterCourses")
    public ResponseEntity<Long> countAllCoursers(){
        return ResponseEntity.ok(statisticAdminService.counterAllCourses());
    }

    @ApiOperation(value = "Count Users doing a road")
    @GetMapping("/counter/user/doing/road/{name}")
    public ResponseEntity<Long> counterUserDoingRoad(@PathVariable String name){
        return ResponseEntity.ok(statisticAdminService.counterUserDoingRoad(name));
    }

    @ApiOperation(value = "Count Users done a road")
    @GetMapping("/counter/user/done/road/{name}")
    public ResponseEntity<Long> counterUserDoneRoad(@PathVariable String name){
        return ResponseEntity.ok(statisticAdminService.counterUserDoneRoad(name));
    }

    @ApiOperation(value = "Counter Users didnt a road")
    @GetMapping("/counter/user/didnt/road/{name}")
    public ResponseEntity<Long> counterUserDidntRoad(@PathVariable String name){
        return ResponseEntity.ok(statisticAdminService.counterUserDidntRoad(name));
    }

    @ApiOperation(value = "Counter Users doing a theme")
    @GetMapping("/counter/user/doing/theme/{name}")
    public ResponseEntity<Long> counterUserDoingTheme(@PathVariable String name){
        return ResponseEntity.ok(statisticAdminService.counterUserDoingTheme(name));
    }

    @ApiOperation(value = "Counter Users done a theme")
    @GetMapping("/counter/user/done/theme/{name}")
    public ResponseEntity<Long> counterUserDoneTheme(@PathVariable String name){
        return ResponseEntity.ok(statisticAdminService.counterUserDoneTheme(name));
    }

    @ApiOperation(value = "Counter Users didnt a theme")
    @GetMapping("/counter/user/didnt/theme/{name}")
    public ResponseEntity<Long> counterUsersDidntTheme(@PathVariable String name){
        return ResponseEntity.ok(statisticAdminService.counterUserDidntTheme(name));
    }

    @ApiOperation(value = "Statics Themes to user by Road")
    @GetMapping("/counter/statitcs/roads/{id}")
    public ResponseEntity<List<StatisticDTO>> statistTheme(@PathVariable String id){
        return ResponseEntity.ok(statisticAdminService.statisticThemeByRoad(id));
    }
    @ApiOperation(value = "Statics Courses to user by Theme")
    @GetMapping("/counter/statistic/themes/{id}")
    public ResponseEntity<List<StatisticDTO>> statisticCourseByTeme(@PathVariable String id){
        return ResponseEntity.ok(statisticAdminService.statisticCourseBytheme(id));
    }

    @ApiOperation(value = "Fetch a statistic course by title")
    @GetMapping("/counter/statistic/titles/{title}")
    public ResponseEntity<List<StatisticDTO>> getCoursesByTitle(@PathVariable String title){
        return ResponseEntity.ok(statisticAdminService.findCourseByTitle(title));
    }




}
