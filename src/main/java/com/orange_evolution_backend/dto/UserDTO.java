package com.orange_evolution_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {
    private Long id;
	private String name;
	private String email;
	private String actualJob;
	private Boolean isAdmin;
}
