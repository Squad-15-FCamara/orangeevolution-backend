package com.orange_evolution_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class StatisticDTO {

    private String name;
    private Long doing;
    private Long done;
    private Long didnt;
    
}
