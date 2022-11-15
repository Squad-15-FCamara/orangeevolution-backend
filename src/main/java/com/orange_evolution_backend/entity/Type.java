// Este é o objeto para salvar os Tipos de conteudos, nele será salvo o Id, Nome.

package com.orange_evolution_backend.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table
public class Type {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_Id")
    private Long id;
    
    @NotBlank
    @Size(max = 20)
    @Column
    private String name;

    @OneToMany(mappedBy = "type")
    private Collection<Course> courses;

}
