package com.betacom.jdbc.process;

import com.betacom.jdbc.exceptions.AcademyException;
import com.betacom.jdbc.singletone.SQLConfiguration;

public class ProcessJDBC {

	public boolean execute() {
		System.out.println("Begin ProcessJDBC");
		
		try {
			SQLConfiguration.getInstance().getConnection();
			System.out.println("Connection with db ok");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		return true;
	}
	
}
