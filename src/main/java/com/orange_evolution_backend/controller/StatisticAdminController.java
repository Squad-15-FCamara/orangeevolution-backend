package com.orange_evolution_backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping("/courseDoing/{idCourse}")
    public ResponseEntity<Long> countUserdoingCourseByIdCourse(@PathVariable Long idCourse) {
        return ResponseEntity.ok(statisticAdminService.counterUserDoing(idCourse));
    }

    @ApiOperation(value = "Count Users done a course")
    @GetMapping("/courseDone/{idCourse}")
    public ResponseEntity<Long> countUserDoneCourseByIdCourse(@PathVariable Long idCourse) {
        return ResponseEntity.ok(statisticAdminService.counterUserDone(idCourse));
    }

    @ApiOperation(value = "Count Users didnt a course")
    @GetMapping("/courseDidnt/{idCourse}")
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
}
