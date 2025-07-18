package com.betacom.car.dao;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import com.betacom.car.models.Moto;
import com.betacom.car.singletone.SQLConfiguration;
import com.betacom.car.utilities.SQLManager;

public class MotoDAO {
	private SQLManager db = new SQLManager();
	
	public int insert(Object[] parameters) throws Exception{
		int numero = 0;
		
		String qry = SQLConfiguration.getInstance().getQuery("moto.insert");
		System.out.println("Query:" + qry);
		
		numero = db.update(qry, parameters,true);
		System.out.println("insert moto ok "+numero);
		return numero;
	}
	
	public int delete(Object[] parameters) throws Exception{
		int numero = 0;
		
		String qry = SQLConfiguration.getInstance().getQuery("moto.delete");
		System.out.println("Query:" + qry);
		
		numero = db.update(qry, parameters);
		
		
		return numero;
	}
	public int update(Object[] parameters) throws Exception{
		int numero = 0;
		
		String qry = SQLConfiguration.getInstance().getQuery("moto.insert");
		System.out.println("Query:" + qry);
		
		numero = db.update(qry, parameters);
		
		return numero;
	}
	public List<Moto> findAll() throws Exception{
		
		String qry = SQLConfiguration.getInstance().getQuery("moto");
		System.out.println("Query:" + qry);
		
		List<Map<String, Object>> lD = db.list(qry);
	

		return lD.stream()
				.map(row -> new Moto(
						(Integer)row.get("id_moto"),  
						(Integer)row.get("id_veicolo"),
						(String)row.get("targa"),
						(Integer)row.get("cilindrata"))).collect(Collectors.toList());
		
	}
	
	public Optional<Moto>   findById(Object[] parameters) throws Exception{
		
		String qry = SQLConfiguration.getInstance().getQuery("moto.byId");
		//System.out.println("Query:" + qry);
		
		Map<String, Object> row = db.get(qry, parameters);
		if (row == null)
			return Optional.empty();
		else {
		return  Optional.ofNullable(new Moto(
				(Integer)row.get("id_moto"),  
				(Integer)row.get("id_veicolo"),
				(String)row.get("targa"),
				(Integer)row.get("cilindrata")));
		}
	}
	
}
