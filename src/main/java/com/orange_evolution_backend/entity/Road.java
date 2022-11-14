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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Road {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "road_Id")
    private Long id;
    
    @Size(max = 20)
    @NotBlank
    @Column
    private String name;

    @OneToMany(mappedBy = "road")
    private Collection<Course> courses;

    @OneToMany(mappedBy = "roadTheme")
    private Collection<Theme> themes;
    
    @ManyToMany
    @JoinTable(name = "doingRoadUser",
    joinColumns = @JoinColumn(name = "road_id"),
    inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Collection<User> userDoingRoad;

    @ManyToMany
    @JoinTable(name = "doneRoadUser",
    joinColumns = @JoinColumn(name = "road_id"),
    inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Collection<User> userDoneRoad;

}
