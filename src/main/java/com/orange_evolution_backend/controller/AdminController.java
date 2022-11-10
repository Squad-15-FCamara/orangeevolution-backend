package com.orange_evolution_backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orange_evolution_backend.entity.Road;
import com.orange_evolution_backend.entity.Theme;
import com.orange_evolution_backend.entity.Type;
import com.orange_evolution_backend.service.AdminService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

@CrossOrigin(origins = "*")
@RequestMapping("/adminServices")
@RestController
@AllArgsConstructor
@Api(description = "Admin Services HTTP methods", tags = "Admin Services")
public class AdminController {
    AdminService adminService;

    @ApiOperation(value = "save a new Road")
    @PostMapping("/road")
    public ResponseEntity<Road> createRoad(@RequestBody Road road){
        return new ResponseEntity<Road>(adminService.saveRoad(road), HttpStatus.CREATED);
    }

    @ApiOperation(value = "save a new Theme")
    @PostMapping("/theme")
    public ResponseEntity<Theme> createTheme(@RequestBody Theme theme){
        return new ResponseEntity<Theme>(adminService.saveTheme(theme), HttpStatus.CREATED);
    }

    @ApiOperation(value = "save a new Type")
    @PostMapping("/type")
    public ResponseEntity<Type> createType(@RequestBody Type type){
        return new ResponseEntity<Type>(adminService.saveType(type), HttpStatus.CREATED);
    }
}
