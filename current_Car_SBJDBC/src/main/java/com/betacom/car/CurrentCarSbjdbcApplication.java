package com.betacom.car;

import static com.betacom.car.utilities.Utils.readRecord;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.betacom.car.process.StartVeicolo;

@SpringBootApplication
public class CurrentCarSbjdbcApplication {

	@Autowired
	private StartVeicolo sv;
	
	public static void main(String[] args) {
		SpringApplication.run(CurrentCarSbjdbcApplication.class, args);
		
	}
	@Bean
	 CommandLineRunner commandLineRunner () {
        return args -> {
        	String path = "C:/Users/Hp/Desktop/eclipse/betacomws/giorno2/current_Car_SBJDBC/input_car.txt/";
    		List<String> params = readRecord(path);
            sv.execute(params);
        };
    }

}
