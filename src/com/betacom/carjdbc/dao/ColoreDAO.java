package com.betacom.carjdbc.dao;

import java.util.List;
import java.util.Map;

import com.betacom.carjdbc.singletone.SQLConfiguration;
import com.betacom.carjdbc.utilities.SQLManager;

public class ColoreDAO {
private SQLManager db = new SQLManager();
	
	public int findId(Object[] parameters) throws Exception{
			int numero = 0;
			
			String qry = SQLConfiguration.getInstance().getQuery("colore.getId");
			System.out.println("Query:" + qry);
			
			List<Map<String, Object>> lA = db.list(qry,parameters);
			int id = ((Number) lA.get(0).get("id_colere")).intValue();
			return id;
		}
}
