package com.betacom.carjdbc.utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class Utils {

	public static List<String> readRecord(String path){
		List<String> r = new ArrayList<String>();
		
		try (BufferedReader reader = new BufferedReader(new FileReader(path))){
			String line = reader.readLine();
			while (line != null) {
				r.add(line);
				line = reader.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return r;
		
	}
	
	public static LocalDate dateToLocalDate(Object value) {
	    if (value == null) return null;

	    if (value instanceof java.sql.Date) {
	        return ((java.sql.Date) value).toLocalDate();
	    } else if (value instanceof java.util.Date) {
	        return ((java.util.Date) value).toInstant()
	                                       .atZone(ZoneId.systemDefault())
	                                       .toLocalDate();
	    } else if (value instanceof String) {
	        return LocalDate.parse((String) value); // assume formato yyyy-MM-dd
	    } else {
	        System.err.println("Tipo non gestito: " + value.getClass());
	        return null;
	    }
			
	} 

}
