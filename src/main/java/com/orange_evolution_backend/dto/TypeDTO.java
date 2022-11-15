// Este objeto serve para retornar os atributos dos Tipos de forma que não tenha interação 
// com as relações, para que tenha somente as informações necessárias para o Front.

package com.orange_evolution_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TypeDTO {
    
    private Long id;
    private String name;

}
