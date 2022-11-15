//Este objeto serve para retornar um objeto que entrega as estátisticas de algum objeto que seja solicitado

package com.orange_evolution_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class StatisticDTO {

    private String name;
    private String idRoad;
    private Long doing;
    private Long done;
    private Long didnt;
    
}
