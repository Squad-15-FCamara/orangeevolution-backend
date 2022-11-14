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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Theme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "theme_Id")
    private Long id;
    
    @Size(max = 20)
    @NotBlank
    @Column
    private String name;

    @OneToMany(mappedBy = "theme")
    private Collection<Course> courses;

    @ManyToOne
    @JoinColumn(name = "road_Id")
    private Road roadTheme;

    @ManyToMany
    @JoinTable(name = "doingTheme",
    joinColumns = @JoinColumn(name = "theme_id"),
    inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Collection<User> userDoingTheme;

    @ManyToMany
    @JoinTable(name = "doneTheme",
    joinColumns = @JoinColumn(name = "theme_id"),
    inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Collection<User> userDoneTheme;
}
