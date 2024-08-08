package com.example.finale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.finale.repository")
@EntityScan(basePackages = "com.example.finale.entities")
public class FinaleApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinaleApplication.class, args);
	}

	@Bean
	public CommandLineRunner run() {
		return args -> {
			// Code to run on startup if needed
		};
	}
}
