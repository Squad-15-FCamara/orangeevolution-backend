package com.orange_evolution_backend.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orange_evolution_backend.dto.CourseDTO;
import com.orange_evolution_backend.dto.CourseStingDTO;
import com.orange_evolution_backend.entity.Course;
import com.orange_evolution_backend.repository.CourseRepository;
import com.orange_evolution_backend.service.AdminService;
import com.orange_evolution_backend.service.CourseService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

@CrossOrigin(origins = "*")
@RequestMapping("/courses")
@RestController
@AllArgsConstructor
@Api(description = "Course HTTP methods", tags = "Courses")
public class CourseController {

    private CourseService courseService;
    private CourseRepository courseRepository;
    private AdminService adminService;
    private ModelMapper modelMapper;

    @ApiOperation(value = "Fetch all courses")
    @GetMapping
    public ResponseEntity<List<CourseStingDTO>> getAllCourses() {
        List<Course> courses = courseService.findAllCourses();
        return ResponseEntity.ok(converCoursesStringToListDTO(courses));
    }

    @ApiOperation(value = "Fetch a course by ID")
    @GetMapping("/{idCourse}")
    public ResponseEntity<CourseStingDTO> getCourseById(@PathVariable Long idCourse) {
        Course course = courseService.findCourseByID(idCourse);
        return ResponseEntity.ok(convertCourseStringDTO(course));
    }

    @ApiOperation(value = "Fetch a course by time atribute")
    @GetMapping("/times/{time}")
    public ResponseEntity<List<CourseStingDTO>> getCoursesByTime(@PathVariable Long time) {
        List<Course> courses = courseService.findCourseByTime(time);
        return ResponseEntity.ok(converCoursesStringToListDTO(courses));
    }

    @ApiOperation(value = "Fetch a course by tag")
    @GetMapping("/tags/{tag}")
    public ResponseEntity<List<CourseStingDTO>> getCoursesByTag(@PathVariable String tag) {
        List<Course> courses = courseService.findCourseByTag(tag);
        return ResponseEntity.ok(converCoursesStringToListDTO(courses));
    }

    @ApiOperation(value = "Fetch a course by road")
    @GetMapping("/roads/{road}")

    public ResponseEntity<List<CourseStingDTO>> getCoursesByRoad(@PathVariable String road) {
        List<Course> courses = adminService.findAllCoursesByRoad(road);
        return ResponseEntity.ok(converCoursesStringToListDTO(courses));

    }

    @ApiOperation(value = "Fetch a course by theme")
    @GetMapping("/themes/{theme}")
    public ResponseEntity<List<CourseStingDTO>> getCoursesByTheme(@PathVariable String theme) {
        List<Course> courses = adminService.findAllCoursesByTheme(theme);
        return ResponseEntity.ok(converCoursesStringToListDTO(courses));

    }

    @ApiOperation(value = "Fetch a course by author")
    @GetMapping("/authors/{author}")
    public ResponseEntity<List<CourseStingDTO>> getCoursesByAuthor(@PathVariable String author) {
        List<Course> courses = courseService.findCoursesByAuthor(author);
        return ResponseEntity.ok(converCoursesStringToListDTO(courses));
    }

    @ApiOperation(value = "Fetch a course by type")
    @GetMapping("/types/{type}")

    public ResponseEntity<List<CourseStingDTO>> getCoursesByType(@PathVariable String type) {
        List<Course> courses = adminService.findAllCoursesByType(type);
        return ResponseEntity.ok(converCoursesStringToListDTO(courses));
    }

    @ApiOperation(value = "Fetch a course by title")
    @GetMapping("/titles/{title}")
    public ResponseEntity<List<CourseStingDTO>> getCoursesByTitle(@PathVariable String title){
        List<Course> courses = courseService.findCourseByTitle(title);
        return ResponseEntity.ok(converCoursesStringToListDTO(courses));
    }

    @ApiOperation(value = "Create a course")
    @PostMapping
    public ResponseEntity<CourseStingDTO> createCourse(@RequestBody CourseStingDTO courseStringDTO) {
        Course course = converCourseStringToEntity(courseStringDTO);
        Course saved = courseService.saveCourse(course);
        return new ResponseEntity<CourseStingDTO>(convertCourseStringDTO(saved), HttpStatus.CREATED);
    }



    @ApiOperation(value = "Delete a course")
    @DeleteMapping("/{courseId}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long courseId) {
        if (!courseRepository.existsById(courseId)) {
            return ResponseEntity.notFound().build();
        }

        courseService.deleteCourse(courseId);

        return ResponseEntity.noContent().build();
    }

    public List<CourseDTO> converCoursesToListDTO(List<Course> courses){
        List<CourseDTO> returnCoursesDTO = new ArrayList<>();
        courses.forEach(course ->{
            returnCoursesDTO.add(modelMapper.map(course, CourseDTO.class));
        });
        return returnCoursesDTO;
    }

    public CourseDTO convertCourseToDTO(Course course){
        return modelMapper.map(course, CourseDTO.class);
    }
    public Course converCourseToEntity(CourseDTO courseDTO){
        return modelMapper.map(courseDTO, Course.class);
    }


    public List<CourseStingDTO> converCoursesStringToListDTO(List<Course> courses){
        List<CourseStingDTO> returnCoursesDTO = new ArrayList<>();
        courses.forEach(course ->{
            CourseStingDTO courseStingDTO = modelMapper.map(course, CourseStingDTO.class);
            CourseDTO courseDTO = modelMapper.map(course,CourseDTO.class);
            courseStingDTO.setIdRoad(adminService.nameRoad(courseDTO.getIdRoad()));
            courseStingDTO.setIdTheme(adminService.nameTheme(courseDTO.getIdTheme()));
            courseStingDTO.setIdType(adminService.nameType(courseDTO.getIdType()));
            returnCoursesDTO.add(courseStingDTO);
        });
        return returnCoursesDTO;
    }

    public CourseStingDTO convertCourseStringDTO(Course course){
        CourseStingDTO courseStingDTO = modelMapper.map(course, CourseStingDTO.class);
        CourseDTO courseDTO = modelMapper.map(course,CourseDTO.class);
        courseStingDTO.setIdRoad(adminService.nameRoad(courseDTO.getIdRoad()));
        courseStingDTO.setIdTheme(adminService.nameTheme(courseDTO.getIdTheme()));
        courseStingDTO.setIdType(adminService.nameType(courseDTO.getIdType()));
        return courseStingDTO;
    }


    public Course converCourseStringToEntity(CourseStingDTO courseStringDTO){
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setIdRoad(adminService.findIdRoadByName(courseStringDTO.getIdRoad()));
        courseDTO.setIdTheme(adminService.findIdThemeByName(courseStringDTO.getIdTheme()));
        courseDTO.setIdType(adminService.findIdTypeByName(courseStringDTO.getIdType()));
        courseDTO.setId(courseStringDTO.getId());
        courseDTO.setAuthor(courseStringDTO.getAuthor());
        courseDTO.setLink(courseStringDTO.getLink());
        courseDTO.setTitle(courseStringDTO.getTitle());
        courseDTO.setTags(courseStringDTO.getTags());
        courseDTO.setTime(courseStringDTO.getTime());
        courseDTO.setDescription(courseStringDTO.getDescription());
        return modelMapper.map(courseDTO, Course.class);
    }

}
