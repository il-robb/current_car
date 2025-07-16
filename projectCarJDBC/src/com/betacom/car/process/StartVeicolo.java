package com.betacom.car.process;

import java.util.List;

import com.betacom.car.exception.AcademyException;
import com.betacom.car.singletone.SQLConfiguration;


public class StartVeicolo {

	public boolean execute(List<String> params) {
		System.out.println("Begin StartVeicolo");
		boolean rc = true;
		try {
			SQLConfiguration.getInstance().getConnection();
			for(String inp:params) {
				String[] oper = inp.split(":");
			
				// scan input and dispatch per operation
			
			
			}
		} catch (Exception e) {
			System.out.println ("Error found:" + e.getMessage());
			rc = false;
			
		}
		try {
			SQLConfiguration.getInstance().closeConnection();
		} catch (AcademyException e) {
			System.out.println("Error in close connection:" + e.getMessage());
		}
		System.out.println("Connection is closed....");
		return rc;
	}
		
		
		
		
		
		
}
