package com.betacom.carjdbc.singletone;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.betacom.carjdbc.exception.AcademyException;
import com.betacom.carjdbc.utilities.SQLManager;


public class SQLConfiguration {
	private static SQLConfiguration instance = null;
	private static Properties prop = new Properties();
	private static Properties queries = new Properties();	
	
	private Connection con = null;  // connection to database
	
	
	private SQLConfiguration() {}
	
	public static SQLConfiguration getInstance() throws AcademyException{
		if (instance == null) {
			instance = new SQLConfiguration();
			loadConfiguration();
		}
		return instance;
	}
	
	private static void loadConfiguration() throws AcademyException{
		try {
			InputStream input = new FileInputStream("src/sql.properties");
			prop.load(input);
			
			InputStream sql = new FileInputStream("src/queries.properties");
			queries.load(sql);
			
			
		} catch (FileNotFoundException e) {
			throw new AcademyException(e.getMessage());
		}  catch (IOException e) {
			throw new AcademyException(e.getMessage());
		}
	}
	
	public String getProperty(String p) {
		return prop.getProperty(p);
	}

	public String getQuery(String p) {
		return queries.getProperty(p);
	}

	
	public Connection getConnection() throws AcademyException{
		if (con == null) {
			con = new SQLManager().getConnection();
		}
		return con;
	}
	/*
	 * Close connection
	 */
	public void closeConnection() throws AcademyException {
		try {
			if (con != null) {
				con.close();
			}
		} catch (Exception e) {
			throw new AcademyException(e.getMessage());
		}
	}
	
	
	
	public void setAutoCommit() throws SQLException{
		con.setAutoCommit(true);
	}

	public void setTransaction() throws SQLException{
		con.setAutoCommit(false);
	}

}
