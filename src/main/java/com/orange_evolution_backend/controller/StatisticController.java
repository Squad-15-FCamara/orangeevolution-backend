package com.orange_evolution_backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orange_evolution_backend.entity.Course;
import com.orange_evolution_backend.service.StatisticService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

@CrossOrigin(origins = "*")
@RequestMapping("statistics")
@AllArgsConstructor
@RestController
@Api(description = "Statistic HTTP methods", tags = "Statistics")
public class StatisticController {

    StatisticService statisticService;


    @ApiOperation(value = "add a favorite course")
    @GetMapping("/favorites/{userId}/{courseId}")
    public ResponseEntity<Course> addFavoriteCourse(@PathVariable Long userId, @PathVariable Long courseId) {
        return ResponseEntity.ok(statisticService.favoriteCourse(userId, courseId));
    }
}
