package com.SpringBootSecurity.Swager;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@OpenAPIDefinition
@Configuration
public class SwagerConfig {
	
	@Bean
	public OpenAPI OpenAPI() {
		
		return new OpenAPI().info(new Info().title("Student simple API").version("1.0").description("Tsega api document"));
	}
}