package com.betacom.jdbc;

import com.betacom.jdbc.process.ProcessJDBC;

public class MainJDBC {

	public static void main(String[] args) {
		
		if (new ProcessJDBC().execute()) {
			System.out.println("File normale");
		} else {
			System.out.println("Error found");
		}
		
		
		
		
		
		
		

	}

}
