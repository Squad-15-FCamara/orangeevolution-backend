package com.orange_evolution_backend.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orange_evolution_backend.dto.RoadDTO;
import com.orange_evolution_backend.dto.ThemeDTO;
import com.orange_evolution_backend.dto.TypeDTO;
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
    ModelMapper modelMapper;

    @ApiOperation(value = "save a new Road")
    @PostMapping("/roads")
    public ResponseEntity<RoadDTO> createRoad(@RequestBody RoadDTO roadDTO){
        Road road = convertRoadToEntity(roadDTO);
        Road saved = adminService.saveRoad(road);
        return new ResponseEntity<RoadDTO>(convertRoadToDTO(saved), HttpStatus.CREATED);
    }

    @ApiOperation(value = "save a new Theme")
    @PostMapping("/themes")
    public ResponseEntity<ThemeDTO> createTheme(@RequestBody ThemeDTO themeDTO){
        Theme theme = convertThemeToEntity(themeDTO);
        Theme saved = adminService.saveTheme(theme);
        return new ResponseEntity<ThemeDTO>(convertThemeToDTO(saved), HttpStatus.CREATED);
    }

    @ApiOperation(value = "save a new Type")
    @PostMapping("/types")
    public ResponseEntity<TypeDTO> createType(@RequestBody TypeDTO typeDTO){
        Type type = convertTypeToEntity(typeDTO);
        Type saved = adminService.saveType(type);
        return new ResponseEntity<TypeDTO>(convertTypeToDTO(saved), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Fetch all names of Roads")
    @GetMapping("/roads/names")
    public ResponseEntity<List<String>> getAllNamesRoads(){
        return ResponseEntity.ok(adminService.findListNameRoad());
    }

    @ApiOperation(value ="Fetch all names of themes")
    @GetMapping("/themes/names")
    public ResponseEntity<List<String>> getAllNamesThemes(){
        return ResponseEntity.ok(adminService.findListNameTheme());
    }

    @ApiOperation(value = "Fetch all names of types")
    @GetMapping("/types/names")
    public ResponseEntity<List<String>> getAllNamesTypes(){
        return ResponseEntity.ok(adminService.findListNameType());
    }

    

    private RoadDTO convertRoadToDTO(Road road){
        return modelMapper.map(road, RoadDTO.class);
    }

    private Road convertRoadToEntity(RoadDTO roadDTO){
        return modelMapper.map(roadDTO, Road.class);
    }

    private ThemeDTO convertThemeToDTO(Theme theme){
        return modelMapper.map(theme, ThemeDTO.class);
    }
    public Theme convertThemeToEntity(ThemeDTO themeDTO){
        return modelMapper.map(themeDTO, Theme.class);
    }

    public TypeDTO convertTypeToDTO(Type type){
        return modelMapper.map(type, TypeDTO.class);
    }
    public Type convertTypeToEntity(TypeDTO typeDTO){
        return modelMapper.map(typeDTO, Type.class );
    }
    
}
