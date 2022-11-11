package com.orange_evolution_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseStingDTO {
    private Long id;
    private String title;
    private String idRoad;
    private String idTheme;
    private String idType;
    private String author;
    private Long time;
    private String tags;
    private String link;
    private String description;
    
}
