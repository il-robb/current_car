package com.betacom.car.dao;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.betacom.car.models.Bici;
import com.betacom.car.singletone.SQLConfiguration;
import com.betacom.car.utilities.SQLManager;

public class BiciDAO {
	private SQLManager db = new SQLManager();
	
	public int insert(Object[] parameters) throws Exception{
		int numero = 0;
		
		String qry = SQLConfiguration.getInstance().getQuery("bici.insert");
		System.out.println("Query:" + qry);    
		
		numero = db.update(qry, parameters,true);
		System.out.println("insert bici ok "+numero);
		
		return numero;
	}
	
	public int delete(Object[] parameters) throws Exception{
		int numero = 0;
		
		String qry = SQLConfiguration.getInstance().getQuery("bici.delete");
		System.out.println("Query:" + qry);
		
		numero = db.update(qry, parameters);
		
		
		return numero;
	}
	
	public int update(Object[] parameters) throws Exception{
		int numero = 0;
		
		String qry = SQLConfiguration.getInstance().getQuery("bici.update");
		System.out.println("Query:" + qry);
		
		numero = db.update(qry, parameters);
		
		return numero;
	}
	public List<Bici> findAll() throws Exception{
		
		String qry = SQLConfiguration.getInstance().getQuery("bici");
		System.out.println("Query:" + qry);
		
		List<Map<String, Object>> lD = db.list(qry);
	

		return lD.stream()
				.map(row -> new Bici(
						(Integer)row.get("id_bici"),  
						(Integer)row.get("id_veicolo"),
						(Integer)row.get("n_marce"),
						(Integer)row.get("id_sospensione"),
						(boolean)row.get("pieghevole"))).collect(Collectors.toList());
		
	}
	
	public Optional<Bici>   findById(Object[] parameters) throws Exception{
		
		String qry = SQLConfiguration.getInstance().getQuery("bici.byId");
		//System.out.println("Query:" + qry);
		
		Map<String, Object> row = db.get(qry, parameters);
		if (row == null)
			return Optional.empty();
		else {
		return  Optional.ofNullable(new Bici(
				(Integer)row.get("id_bici"),  
				(Integer)row.get("id_veicolo"),
				(Integer)row.get("n_marce"),
				(Integer)row.get("id_sospensione"),
				(boolean)row.get("pieghevole")));
		}
	}
	
}
