package com.betacom.carjdbc.dao;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.betacom.carjdbc.models.Macchina;
import com.betacom.carjdbc.singletone.SQLConfiguration;
import com.betacom.carjdbc.utilities.SQLManager;

public class MacchinaDAO {
	
	
	private SQLManager db = new SQLManager();
	public VeicoloDAO daoV = new VeicoloDAO();
	
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
		daoV.delete("veicolo.delete", parameters);
		
		return numero;
	}

	
	public List<Macchina> findAll() throws Exception{
		
		String qry = SQLConfiguration.getInstance().getQuery("macchina");
		System.out.println("Query:" + qry);
		
		List<Map<String, Object>> lM = db.list(qry);
	
		return lM.stream()
				.map(row -> new Macchina(
						(Integer)row.get("id_macchina"),
						(Integer)row.get("id_veicolo"),
						(Integer)row.get("n_porte"), 
						(String)row.get("targa"), 
						(Integer)row.get("cilindrata"))).collect(Collectors.toList());
		
	}
	
	public List<Macchina> findGeneric(String qryName,Object[] parameters) throws Exception{
		
		String qry = SQLConfiguration.getInstance().getQuery(qryName);
		System.out.println("Query:" + qry);
		
		List<Map<String, Object>> lM = db.list(qry, parameters);
	
		return lM.stream()
				.map(row -> new Macchina(
						(Integer)row.get("id_macchina"),
						(Integer)row.get("id_veicolo"),
						(Integer)row.get("n_porte"), 
						(String)row.get("targa"), 
						(Integer)row.get("cilindrata"))).collect(Collectors.toList());
		
	}

	
	public Optional<Macchina> findById(Object[] parameters) throws Exception{
		
		String qry = SQLConfiguration.getInstance().getQuery("macchina.byId");
		System.out.println("Query:" + qry);
		
		Map<String, Object> row = db.get(qry, parameters);
		if (row == null)
			return Optional.empty();
		else {
			return  Optional.ofNullable(new Macchina(
					(Integer)row.get("id_macchina"),
					(Integer)row.get("id_veicolo"),
					(Integer)row.get("n_porte"), 
					(String)row.get("targa"), 
					(Integer)row.get("cilindrata")));
		}
	}
}
