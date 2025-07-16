package com.betacom.carjdbc.dao;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.betacom.carjdbc.models.Veicolo;
import com.betacom.carjdbc.singletone.SQLConfiguration;
import com.betacom.carjdbc.utilities.SQLManager;

public class VeicoloDAO {
 
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

	
	public List<Veicolo> findAll() throws Exception{
		
		String qry = SQLConfiguration.getInstance().getQuery("veicolo");
		System.out.println("Query:" + qry);
		
		List<Map<String, Object>> lM = db.list(qry);
	
		return lM.stream()
				.map(row -> new Veicolo(
						(Integer)row.get("id_veicolo"),
						(String)row.get("tipo_veicolo"), 
						(Integer)row.get("numero_ruote"), 
						(Integer)row.get("id_alimentazione"), 
						(Integer)row.get("id_categoria"),
						(Integer)row.get("id_colore"),
						(Integer)row.get("id_marca"),
						(Integer)row.get("anno_prod"),
						(String)row.get("modello"))).collect(Collectors.toList());
		
	}
	
	public List<Veicolo> findGeneric(String qryName,Object[] parameters) throws Exception{
		
		String qry = SQLConfiguration.getInstance().getQuery(qryName);
		System.out.println("Query:" + qry);
		
		List<Map<String, Object>> lM = db.list(qry, parameters);
	
		return lM.stream()
				.map(row -> new Veicolo(
						(Integer)row.get("id_veicolo"),
						(String)row.get("tipo_veicolo"), 
						(Integer)row.get("numero_ruote"), 
						(Integer)row.get("id_alimentazione"), 
						(Integer)row.get("id_categoria"),
						(Integer)row.get("id_colore"),
						(Integer)row.get("id_marca"),
						(Integer)row.get("anno_prod"),
						(String)row.get("modello"))).collect(Collectors.toList());
		
	}

	
	public Optional<Veicolo> findById(Object[] parameters) throws Exception{
		
		String qry = SQLConfiguration.getInstance().getQuery("veicolo.byId");
		System.out.println("Query:" + qry);
		
		Map<String, Object> row = db.get(qry, parameters);
		if (row == null)
			return Optional.empty();
		else {
			return  Optional.ofNullable(new Veicolo(
					(Integer)row.get("id_veicolo"),
					(String)row.get("tipo_veicolo"), 
					(Integer)row.get("numero_ruote"), 
					(Integer)row.get("id_alimentazione"), 
					(Integer)row.get("id_categoria"),
					(Integer)row.get("id_colore"),
					(Integer)row.get("id_marca"),
					(Integer)row.get("anno_prod"),
					(String)row.get("modello")));
		}
	}
}
