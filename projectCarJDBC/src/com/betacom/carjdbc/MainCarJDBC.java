package com.betacom.carjdbc;

import static com.betacom.carjdbc.utilities.Utils.readRecord;

import java.util.List;

import com.betacom.carjdbc.process.StartVeicolo;


public class MainCarJDBC {
public static void main(String[] args) {
		
		String path = "src/input_car.txt";
		List<String> params = readRecord(path);
		
		if (new StartVeicolo().execute(params)) {
			System.out.println("fine normale");
		} else {
			System.out.println("Error found");
		}
	}
}
