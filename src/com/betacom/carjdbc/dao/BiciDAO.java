package com.betacom.carjdbc.dao;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import com.betacom.carjdbc.models.Bici;

import com.betacom.carjdbc.singletone.SQLConfiguration;
import com.betacom.carjdbc.utilities.SQLManager;

public class BiciDAO {
	
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

	
	public List<Bici> findAll() throws Exception{
		
		String qry = SQLConfiguration.getInstance().getQuery("bici");
		System.out.println("Query:" + qry);
		
		List<Map<String, Object>> lB = db.list(qry);
	
		return lB.stream()
				.map(row -> new Bici(
						(Integer)row.get("id_bici"),
						(Integer)row.get("id_veicolo"),
						(Integer)row.get("n_marce"), 
						(Integer)row.get("id_sospensioni"), 
						(Boolean)row.get("pieghevole"))).collect(Collectors.toList());
		
	}
	
	public List<Bici> findGeneric(String qryName,Object[] parameters) throws Exception{
		
		String qry = SQLConfiguration.getInstance().getQuery(qryName);
		System.out.println("Query:" + qry);
		
		List<Map<String, Object>> lB = db.list(qry, parameters);
	
		return lB.stream()
				.map(row -> new Bici(
						(Integer)row.get("id_bici"),
						(Integer)row.get("id_veicolo"),
						(Integer)row.get("n_marce"), 
						(Integer)row.get("id_sospensioni"), 
						(Boolean)row.get("pieghevole"))).collect(Collectors.toList());
		
	}

	
	public Optional<Bici> findById(Object[] parameters) throws Exception{
		
		String qry = SQLConfiguration.getInstance().getQuery("bici.byId");
		System.out.println("Query:" + qry);
		
		Map<String, Object> row = db.get(qry, parameters);
		if (row == null)
			return Optional.empty();
		else {
			return  Optional.ofNullable(new Bici(
					(Integer)row.get("id_bici"),
					(Integer)row.get("id_veicolo"),
					(Integer)row.get("n_marce"), 
					(Integer)row.get("id_sospensioni"), 
					(Boolean)row.get("pieghevole")));
		}
	}
}
