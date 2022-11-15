package com.orange_evolution_backend.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orange_evolution_backend.dto.RoadDTO;
import com.orange_evolution_backend.dto.ThemeDTO;
import com.orange_evolution_backend.dto.ThemeStringDTO;
import com.orange_evolution_backend.dto.TypeDTO;
import com.orange_evolution_backend.entity.Road;
import com.orange_evolution_backend.entity.Theme;
import com.orange_evolution_backend.entity.Type;
import com.orange_evolution_backend.repository.RoadRepoistory;
import com.orange_evolution_backend.repository.ThemeRepository;
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
    RoadRepoistory roadRepoistory;
    ModelMapper modelMapper;
    ThemeRepository themeRepository;

    @ApiOperation(value = "Save a new Road")
    @PostMapping("/roads")
    public ResponseEntity<RoadDTO> createRoad(@RequestBody RoadDTO roadDTO){
        Road road = convertRoadToEntity(roadDTO);
        Road saved = adminService.saveRoad(road);
        return new ResponseEntity<RoadDTO>(convertRoadToDTO(saved), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Save a new Theme")
    @PostMapping("/themes")
    public ResponseEntity<ThemeStringDTO> createTheme(@RequestBody ThemeDTO themeStringDTO){
        Theme theme = convertThemeToEntity(themeStringDTO);
        Theme saved = adminService.saveTheme(theme);
        return new ResponseEntity<ThemeStringDTO>(convertThemeStringToDTO(saved), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Save a new Type")
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

    @ApiOperation(value = "Fetch a theme")
    @GetMapping("/themes/by/road/{nameThema}")
    public ResponseEntity<ThemeDTO> getthemes(@PathVariable String nameThema){
        Theme theme = themeRepository.findByName(nameThema);
        return ResponseEntity.ok(convertThemeToDTO(theme));
    }



    @ApiOperation(value = "Update a theme")
    @PutMapping("/update/theme/{nameTheme}")
    public ResponseEntity<ThemeDTO> updateTheme(@RequestBody ThemeDTO themeDTO, @PathVariable Long nameTheme){
        Theme theme = convertThemeToEntity(themeDTO);
        Theme saved = adminService.updaTheme(theme, nameTheme);
        return new ResponseEntity<ThemeDTO>(convertThemeToDTO(saved),HttpStatus.ACCEPTED);
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

    private Theme convertThemeToEntity(ThemeDTO themeDTO){
        return modelMapper.map(themeDTO, Theme.class);
    }

    private ThemeStringDTO convertThemeStringToDTO(Theme theme){
        ThemeDTO themeDTO = convertThemeToDTO(theme);
        ThemeStringDTO themeStringDTO = modelMapper.map(theme, ThemeStringDTO.class);
        themeStringDTO.setNameRoad(adminService.nameRoad(themeDTO.getIdRoad()));
        return themeStringDTO;
    }
    public Theme convertThemeStringToEntity(ThemeStringDTO themeStringDTO){
        ThemeDTO themeDTO = new ThemeDTO();
        themeDTO.setId(themeRepository.findByName(themeStringDTO.getName()).getId());
        themeDTO.setName(themeStringDTO.getName());
        themeDTO.setIdRoad(roadRepoistory.findByName(themeStringDTO.getNameRoad()).getId());
        return modelMapper.map(themeDTO, Theme.class);
    }

    public TypeDTO convertTypeToDTO(Type type){
        return modelMapper.map(type, TypeDTO.class);
    }
    public Type convertTypeToEntity(TypeDTO typeDTO){
        return modelMapper.map(typeDTO, Type.class );
    }
    
}
