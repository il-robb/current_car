package com.betacom.carjdbc.dao;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.betacom.carjdbc.models.Moto;
import com.betacom.carjdbc.singletone.SQLConfiguration;
import com.betacom.carjdbc.utilities.SQLManager;

public class MotoDAO {

private SQLManager db = new SQLManager();
	
	
	public int insert(String qryName, Object[] parameters) throws Exception{
		int numero = 0;
		
		String qry = SQLConfiguration.getInstance().getQuery(qryName);
		System.out.println("Query:" + qry);
		
		numero = db.update(qry, parameters, true);
		
		return numero;
	}

	public int update(String qryName, Object[] parameters) throws Exception{
		int numero = 0;
		
		String qry = SQLConfiguration.getInstance().getQuery(qryName);
		System.out.println("Query:" + qry);
		
		numero = db.update(qry, parameters);
		
		return numero;
	}

	
	public int delete(String qryName, Object[] parameters) throws Exception{
		int numero = 0;
		
		String qry = SQLConfiguration.getInstance().getQuery(qryName);
		System.out.println("Query:" + qry);
		
		numero = db.update(qry, parameters);
		
		return numero;
	}

	
	public List<Moto> findAll() throws Exception{
		
		String qry = SQLConfiguration.getInstance().getQuery("moto");
		System.out.println("Query:" + qry);
		
		List<Map<String, Object>> lM = db.list(qry);
	
		return lM.stream()
				.map(row -> new Moto(
						(Integer)row.get("moto"),
						(Integer)row.get("id_veicolo"),
						(String)row.get("targa"), 
						(Integer)row.get("cilindrata"))).collect(Collectors.toList());
		
	}
	
	public List<Moto> findGeneric(String qryName,Object[] parameters) throws Exception{
		
		String qry = SQLConfiguration.getInstance().getQuery(qryName);
		System.out.println("Query:" + qry);
		
		List<Map<String, Object>> lM = db.list(qry, parameters);
	
		return lM.stream()
				.map(row -> new Moto(
						(Integer)row.get("moto"),
						(Integer)row.get("id_veicolo"),
						(String)row.get("targa"), 
						(Integer)row.get("cilindrata"))).collect(Collectors.toList());
		
	}

	
	public Optional<Moto> findById(Object[] parameters) throws Exception{
		
		String qry = SQLConfiguration.getInstance().getQuery("moto.byId");
		System.out.println("Query:" + qry);
		
		Map<String, Object> row = db.get(qry, parameters);
		if (row == null)
			return Optional.empty();
		else {
			return  Optional.ofNullable(new Moto(
					(Integer)row.get("moto"),
					(Integer)row.get("id_veicolo"),
					(String)row.get("targa"), 
					(Integer)row.get("cilindrata")));
		}
	}
}
