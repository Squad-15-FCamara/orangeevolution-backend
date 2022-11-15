// Aqui serve como os Endpoints para os serviços de AdminService

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
    // Aqui está chamando os serviços que serão necessários para o funcionamento  da classe, seus contrutores estão sendo gerados de "@AllArgosConstructor"
    AdminService adminService;
    RoadRepoistory roadRepoistory;
    ModelMapper modelMapper;
    ThemeRepository themeRepository;

    // Usa o serviço de AdminService e e também os convertores de DTO para que possa ser salvo dentro do Banco
    // uma nova Trilha, mesmo que o Front passe apenas as informações do DTO
    @ApiOperation(value = "Save a new Road")
    @PostMapping("/roads")
    public ResponseEntity<RoadDTO> createRoad(@RequestBody RoadDTO roadDTO){
        Road road = convertRoadToEntity(roadDTO);
        Road saved = adminService.saveRoad(road);
        return new ResponseEntity<RoadDTO>(convertRoadToDTO(saved), HttpStatus.CREATED);
    }

    // Usa o serviço de AdminService e e também os convertores de DTO para que possa ser salvo dentro do Banco
    // um novo Tema, mesmo que o Front passe apenas as informações do DTO.
    // Deve responder um 200 como resposta HTTP caso tenha sucesso.
    @ApiOperation(value = "Save a new Theme")
    @PostMapping("/themes")
    public ResponseEntity<ThemeStringDTO> createTheme(@RequestBody ThemeDTO themeStringDTO){
        Theme theme = convertThemeToEntity(themeStringDTO);
        Theme saved = adminService.saveTheme(theme);
        return new ResponseEntity<ThemeStringDTO>(convertThemeStringToDTO(saved), HttpStatus.CREATED);
    }

    // Usa o serviço de AdminService e e também os convertores de DTO para que possa ser salvo dentro do Banco
    // um novo Tipo, mesmo que o Front passe apenas as informações do DTO.
    // Deve responder um 200 como resposta HTTP caso tenha sucesso.
    @ApiOperation(value = "Save a new Type")
    @PostMapping("/types")
    public ResponseEntity<TypeDTO> createType(@RequestBody TypeDTO typeDTO){
        Type type = convertTypeToEntity(typeDTO);
        Type saved = adminService.saveType(type);
        return new ResponseEntity<TypeDTO>(convertTypeToDTO(saved), HttpStatus.CREATED);
    }

    // Usa o serviço de AdminService para que possa retornar uma list de Strings com o nome de cada Trilha salva no Banco.
    // Deve responder um 200 como resposta HTTP caso tenha sucesso.
    @ApiOperation(value = "Fetch all names of Roads")
    @GetMapping("/roads/names")
    public ResponseEntity<List<String>> getAllNamesRoads(){
        return ResponseEntity.ok(adminService.findListNameRoad());
    }

    // Usa o serviço de AdminService para que possa retornar uma list de Strings com o nome de cada Tema salva no Banco.
    // Deve responder um 200 como resposta HTTP caso tenha sucesso.
    @ApiOperation(value ="Fetch all names of themes")
    @GetMapping("/themes/names")
    public ResponseEntity<List<String>> getAllNamesThemes(){
        return ResponseEntity.ok(adminService.findListNameTheme());
    }

    // Usa o serviço de AdminService para que possa retornar uma list de Strings com o nome de cada Tipo salva no Banco.
    // Deve responder um 200 como resposta HTTP caso tenha sucesso.
    @ApiOperation(value = "Fetch all names of types")
    @GetMapping("/types/names")
    public ResponseEntity<List<String>> getAllNamesTypes(){
        return ResponseEntity.ok(adminService.findListNameType());
    }

    // Usa o serviço de AdminService para encontrar um Tema por uma requisição de String e depois convert o tipo de Thema para ThemaDTO.
    // Deve responder um 200 como resposta HTTP caso tenha sucesso.
    @ApiOperation(value = "Fetch a theme")
    @GetMapping("/themes/by/road/{nameThema}")
    public ResponseEntity<ThemeDTO> getthemes(@PathVariable String nameThema){
        Theme theme = themeRepository.findByName(nameThema);
        return ResponseEntity.ok(convertThemeToDTO(theme));
    }


    // Recebe um Tema no tipo DTO e também um Id do Tema, assim realiza a conversão de DTO para Tema
    // Em sequência utiliza o serviço de UpdateTheme do AdminService usando o Tema convertido e o Id
    // Por fim responde com um 202 e o Tema em formato DTO com a versão atualizada. 
    @ApiOperation(value = "Update a theme")
    @PutMapping("/update/theme/{nameTheme}")
    public ResponseEntity<ThemeDTO> updateTheme(@RequestBody ThemeDTO themeDTO, @PathVariable Long nameTheme){
        Theme theme = convertThemeToEntity(themeDTO);
        Theme saved = adminService.updaTheme(theme, nameTheme);
        return new ResponseEntity<ThemeDTO>(convertThemeToDTO(saved),HttpStatus.ACCEPTED);
    }

    // Usa de ModelMapper para converter uma Trilha em Trilha DTO.

    private RoadDTO convertRoadToDTO(Road road){
        return modelMapper.map(road, RoadDTO.class);
    }
    // Usa de ModelMapper para converter uma Trilha DTO em Trilha.
    private Road convertRoadToEntity(RoadDTO roadDTO){
        return modelMapper.map(roadDTO, Road.class);
    }

    // Usa de ModelMapper para converter um Tema em Tema DTO.
    private ThemeDTO convertThemeToDTO(Theme theme){
        return modelMapper.map(theme, ThemeDTO.class);
    }

    // Usa de ModelMapper para converter um Tema DTO em Tema.
    private Theme convertThemeToEntity(ThemeDTO themeDTO){
        return modelMapper.map(themeDTO, Theme.class);
    }

    //Usa de ModelMapper para converter um Tema em Tema DTO String.
    private ThemeStringDTO convertThemeStringToDTO(Theme theme){
        ThemeDTO themeDTO = convertThemeToDTO(theme);
        ThemeStringDTO themeStringDTO = modelMapper.map(theme, ThemeStringDTO.class);
        themeStringDTO.setNameRoad(adminService.nameRoad(themeDTO.getIdRoad()));
        return themeStringDTO;
    }

    //Usa de ModelMapper para convertar um Tema String DTO para Tema.
    public Theme convertThemeStringToEntity(ThemeStringDTO themeStringDTO){
        ThemeDTO themeDTO = new ThemeDTO();
        themeDTO.setId(themeRepository.findByName(themeStringDTO.getName()).getId());
        themeDTO.setName(themeStringDTO.getName());
        themeDTO.setIdRoad(roadRepoistory.findByName(themeStringDTO.getNameRoad()).getId());
        return modelMapper.map(themeDTO, Theme.class);
    }

    //Usa de ModelMapper para converter um Tipo em Tipo DTO.
    public TypeDTO convertTypeToDTO(Type type){
        return modelMapper.map(type, TypeDTO.class);
    }

    //Usa de ModelMapper para converter um Tipo DTO em Tipo.
    public Type convertTypeToEntity(TypeDTO typeDTO){
        return modelMapper.map(typeDTO, Type.class );
    }
    
}
