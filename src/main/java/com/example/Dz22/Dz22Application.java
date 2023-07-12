package com.example.Dz22;

import com.example.Dz22.model.Student;
import com.example.Dz22.repository.StudentRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class Dz22Application {


	public static void main(String[] args) {
		SpringApplication.run(Dz22Application.class, args);
	}


}
