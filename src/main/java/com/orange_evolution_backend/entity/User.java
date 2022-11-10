package com.orange_evolution_backend.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
	@Column(name = "user_id")
	private Long id;
	
	@Size(max = 60)
	@NotBlank
	@Column
	private String name;
	
	@Size(max = 200)
	@NotBlank
	@Email
	@Column
	private String email;
	
	@Size(max = 60)
	@NotBlank
	@Column
	private String actualJob;
	
	@NotNull
	@Column
	private Boolean isAdmin;
	
	@ManyToMany(mappedBy = "users")
	private Collection<Course> courses;

	@ManyToMany(mappedBy = "userDoing")
	private Collection<Course> courseDoing;

	@ManyToMany(mappedBy = "userDone")
	private Collection<Course> courseDone;
}
