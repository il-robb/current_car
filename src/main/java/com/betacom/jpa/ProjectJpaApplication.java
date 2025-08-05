package com.betacom.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjectJpaApplication {

//	@Autowired
//	private MainProcess mP;
	
	public static void main(String[] args) {
		SpringApplication.run(ProjectJpaApplication.class, args);
	}

	
//	@Bean
//	CommandLineRunner commandLineRunner() {
//		
//		return args ->{
//			mP.Execute();
//		};
//		
//	}
}
