package com.orange_evolution_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
	private String name;
	private String email;
	private String actualJob;
	private Boolean isAdmin;
}
