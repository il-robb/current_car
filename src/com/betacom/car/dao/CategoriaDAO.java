package com.betacom.car.dao;

import java.util.List;
import java.util.Map;

import com.betacom.car.exception.AcademyException;
import com.betacom.car.singletone.SQLConfiguration;
import com.betacom.car.utilities.SQLManager;

public class CategoriaDAO {

	private SQLManager db = new SQLManager();

	public int findID(String valore) throws AcademyException {

		String qry = SQLConfiguration.getInstance().getQuery("categoria.findId");
		System.out.println("Query: " + qry);
		
		Object[] parameters = new Object[] {valore};
		
		List<Map<String, Object>> lA = db.list(qry, parameters);
		int id = ((Number) lA.get(0).get("id_categoria")).intValue();
		return id;
	}
	
	
}
