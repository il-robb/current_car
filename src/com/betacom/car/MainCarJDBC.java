package com.betacom.car;

import static com.betacom.car.utilities.Utils.readRecord;

import java.util.List;

import com.betacom.car.process.StartVeicolo;


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

//nel list all  faccio "find:"

//add:macchina,4,benzina,strada,bianca,renault,2024,Clio,3,EL123GZ,1200
//update:macchina,1,alimentazione=benzina,colore=rosso
//add:moto,2,benzina,strada,bianco,Yamaha,2021,R1,EL22239,900
//delete:moto,2
//list

//add:moto,2,benzina,strada,bianco,yamaha,2024,moto1,HJ785S,200



