package com.betacom.jdbc.singletone;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

import com.betacom.jdbc.exceptions.AcademyException;
import com.betacom.jdbc.utilities.SQLManager;

public class SQLConfiguration {

	private static SQLConfiguration instance = null;
	private static Properties prop = new Properties();
	
	//voglio che sia sempre connesso
	private Connection con = null;
	
	private SQLConfiguration() {}

	
	/////////////////////////////////
	
	public static SQLConfiguration getInstance() throws AcademyException{
		if (instance == null) {
			instance = new SQLConfiguration();
			loadConfiguration();

		}
		return instance;
	}
	
	// come si gestisce un file di properties
	private static void loadConfiguration() throws AcademyException {
		try {
			InputStream input = new FileInputStream("./sql.properties");
			prop.load(input);
			System.out.println("url:" + prop.getProperty("url"));
			
			
			
		} catch (FileNotFoundException e) {
			throw new AcademyException(e.getMessage());
		} catch (IOException e) {
			throw new AcademyException(e.getMessage());
		}
	}
	
	public String getProperty(String p) {
		return prop.getProperty(p);
	}
	
	
	
	public Connection getConnection() throws Exception {
		if (con == null) {
			con = new SQLManager().getConnection();
		}
		return con;
	}
	
	
	
	
	
	
	
	

}
