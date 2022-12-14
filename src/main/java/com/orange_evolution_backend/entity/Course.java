// // Este é o objeto para os conteudos da aplicação, nele será armazenado os 
// atributos do Id, Titulo,Trilha,Tema,Tipo,Autor ,Tempo ,Tag ,Link e Descricao em sua tabela do Banco de dados.


// // Aqui também poderá ser acessado as tabelas relacionais entre Usuarios e 
// Cursos que são divididas em 3 tableas, as dos conteudos favoritados, em progresso e terminados.

package com.orange_evolution_backend.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Long id;
    
    @Size(max = 200)
	@NotBlank
    @Column
    private String title;
    
    @ManyToOne()
    @JoinColumn(name = "road_Id")
    private Road road;
    
    @ManyToOne()
    @JoinColumn(name = "theme_Id")
    private Theme theme;
    
    @ManyToOne()
    @JoinColumn(name = "type_Id")
    private Type type;
    
    @Size(max = 40)
	@NotBlank
    @Column 
    private String author;
    
    @NotNull
    @Column
    private Long time;
    
    @Size(max = 100)
	@NotBlank
    @Column
    private String tags;
    
    @Size(max = 200)
	@NotBlank
    @Column
    private String link;
    
    @Size(max = 255)
	@NotBlank
    @Column
    private String description;

    @ManyToMany
    @JoinTable(name = "favorite_courses",
    joinColumns = @JoinColumn(name = "course_id"),
    inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Collection<User> users;

    @ManyToMany
    @JoinTable(name = "doing",
    joinColumns = @JoinColumn(name = "course_id"),
    inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Collection<User> userDoing;

    @ManyToMany
    @JoinTable(name = "done",
    joinColumns = @JoinColumn(name = "course_id"),
    inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Collection<User> userDone;
}
