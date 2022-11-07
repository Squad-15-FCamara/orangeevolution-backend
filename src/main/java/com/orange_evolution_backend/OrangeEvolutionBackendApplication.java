package com.orange_evolution_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class OrangeEvolutionBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrangeEvolutionBackendApplication.class, args);
	}

}
