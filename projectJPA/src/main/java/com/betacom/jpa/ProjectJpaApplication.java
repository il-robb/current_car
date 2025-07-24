package com.betacom.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.betacom.jpa.process.MainProcess;

@SpringBootApplication
public class ProjectJpaApplication {

	@Autowired
	private MainProcess mP;
	
	public static void main(String[] args) {
		SpringApplication.run(ProjectJpaApplication.class, args);
	}

	
	@Bean
	CommandLineRunner commandLineRunner() {
		
		return args ->{
			mP.Execute();
		};
		
	}
}
