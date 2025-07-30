package com.betacom.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjectJpaCarApplication  {

//	@Autowired
//	private MainProcess mP;
	
	public static void main(String[] args) {
		SpringApplication.run(ProjectJpaCarApplication.class, args);
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
