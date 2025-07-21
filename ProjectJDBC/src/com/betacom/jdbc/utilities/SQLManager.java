package com.betacom.jdbc.utilities;

import java.sql.Connection;
import java.sql.DriverManager;

import com.betacom.jdbc.singletone.SQLConfiguration;

public class SQLManager {

	public Connection getConnection() throws Exception {
		Connection con = null;

		
		try {
			Class.forName(SQLConfiguration.getInstance().getProperty("driver")); //load driver with reflection
			
			
			/*
			 * open connction with DriverManager di sql
			 * url
			 * user
			 * pwd
			 */
			con = DriverManager.getConnection(
								SQLConfiguration.getInstance().getProperty("url"),
								SQLConfiguration.getInstance().getProperty("user"),
								SQLConfiguration.getInstance().getProperty("pwd")
					);
					
		
			return con;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		
		
	}
	
}
