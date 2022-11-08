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
import javax.persistence.Table;

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

    @Column
    private String title;

    @Column
    private String road;
    
    @Column
    private String theme;

    @Column
    private String type;

    @Column 
    private String author;

    @Column
    private Long time;

    @Column
    private String tags;

    @Column
    private String link;

    @Column
    private String description;

    @ManyToMany
    @JoinTable(name = "favorite_courses",
    joinColumns = @JoinColumn(name = "course_id"),
    inverseJoinColumns = @JoinColumn(name = "userId"))
    Collection<User> users; 
}