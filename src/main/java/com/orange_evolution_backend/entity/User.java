package com.orange_evolution_backend.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userId")
	private Long id;
	
	@Column
	private String name;
	
	@Column
	private String email;
	
	@Column
	private String actualJob;
	
	@Column
	private Boolean isAdmin;
	
	@ManyToMany(mappedBy = "users")
	private Collection<Course> courses;
}
