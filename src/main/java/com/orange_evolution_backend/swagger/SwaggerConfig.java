package com.orange_evolution_backend.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
	
	@Bean
	public Docket docket() {
		
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.orange_evolution_backend.controller"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiInfo()).useDefaultResponseMessages(false);
	}
	
	public ApiInfo apiInfo() {
		
		return new ApiInfoBuilder().title("Orange Evolution API")
				.description("Orange Evolution App Models, Controllers and Endpoints")
				.version("1.0.1")
				.build();
	}
	
}
