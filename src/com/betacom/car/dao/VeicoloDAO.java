package com.betacom.car.dao;

import com.betacom.car.utilities.SQLManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.betacom.car.models.Veicolo;
import com.betacom.car.singletone.SQLConfiguration;

public class VeicoloDAO {
	private SQLManager db = new SQLManager();
	private MacchinaDAO daoM = new MacchinaDAO();
	private BiciDAO daoB = new BiciDAO();
	private MotoDAO daoMo = new MotoDAO();
	
	public int insert(Object[] paramsV, Object[] paramsS) throws Exception{
		int numero = 0;
		
		String qry = SQLConfiguration.getInstance().getQuery("veicolo.insert");
		System.out.println("Query:" + qry);
		
		
		numero = db.update(qry, paramsV,true);
		System.out.println("insert veicolo ok "+numero);
		
		paramsS[0]=numero;
		switch (paramsV[0].toString()){
		case "1": {
			
			daoM.insert(paramsS);
			break;
		}
		case "2": {
			daoMo.insert(paramsS);
			break;
		}
		case "3": {
		
			daoB.insert(paramsS);
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + paramsV[0].toString());
		}
		
		return numero;
	}
	
	
	public int delete(Object[] parameters) throws Exception{
		int numero = 0;
		
		switch (parameters[0].toString()) {
		case "moto": {
			daoMo.delete(new Object[] {parameters[1]});
			break;
		}
		case "macchina": {
			daoM.delete(new Object[] {parameters[1]});
			break;
		}
		case "bici": {
			daoB.delete(new Object[] {parameters[1]});
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected veicolo: " + parameters[1]);
		}
		
		String qry = SQLConfiguration.getInstance().getQuery("veicolo.delete");
		System.out.println("Query:" + qry);
		
		numero = db.update(qry, new Object[] {parameters[1]});
		
		return numero;
	}
	
	
	public int update(Object[] parameters) throws Exception{
		int numero = 0;
		
		String qry = SQLConfiguration.getInstance().getQuery("veicolo.update");
		System.out.println("Query:" + qry);
		
		numero = db.update(qry , parameters);
		
		return numero;
	}
public List<Veicolo> findAll() throws Exception{
		
		String qry = SQLConfiguration.getInstance().getQuery("veicolo");
		//System.out.println("Query:" + qry);
		
		List<Map<String, Object>> lD = db.list(qry);
		
		
		for (Map<String, Object> row : lD) {
			//vei
			String veicQ = SQLConfiguration.getInstance().getQuery("tipo_veicolo.descrizione");
	        String veicDesc=  db.get(veicQ,new Object[] {row.get("id_tipo_veicolo")}).get("descrizione").toString();
	        row.put("id_tipo_veicolo", veicDesc);
			//alim
			String alimQ = SQLConfiguration.getInstance().getQuery("alimentazione.descrizione");
	        String alimDesc=  db.get(alimQ,new Object[] {row.get("id_alimentazione")}).get("descrizione").toString();
	        row.put("id_alimentazione", alimDesc);
	        //cat
	        String catQ = SQLConfiguration.getInstance().getQuery("categoria.descrizione");
	        String catDesc=  db.get(catQ,new Object[] {row.get("id_categoria")}).get("descrizione").toString();
	        row.put("id_categoria", catDesc);
	        //colo
	        String coloQ = SQLConfiguration.getInstance().getQuery("colore.descrizione");
	        String colDesc=  db.get(coloQ,new Object[] {row.get("id_colore")}).get("descrizione").toString();
	        row.put("id_colore", colDesc);
	        //modello
	        String marQ = SQLConfiguration.getInstance().getQuery("marca.descrizione");
	        String marDesc=  db.get(marQ,new Object[] {row.get("id_marca")}).get("descrizione").toString();
	        row.put("id_marca", marDesc);
		}
		
		
		return lD.stream()
				.map(row -> new Veicolo(
						(Integer)row.get("id_veicolo"),
						(String)row.get("id_tipo_veicolo"), 
						(Integer)row.get("numero_ruote"), 
						(String)row.get("id_alimentazione"), 
						(String)row.get("id_categoria"),
						(String)row.get("id_colore"),
						(String)row.get("id_marca"),
						(String)row.get("anno_prod"),
						(String)row.get("modello"))).collect(Collectors.toList());
		
	}
	
public Optional<Veicolo>   findById(Object[] parameters) throws Exception{
		
		String qry = SQLConfiguration.getInstance().getQuery("veicolo.byId");
		System.out.println("Query:" + qry);
		
		Map<String, Object> row = db.get(qry, parameters);
		if (row == null)
			return Optional.empty();
		else {
		return  Optional.ofNullable(new Veicolo(
				(Integer)row.get("id_veicolo"), 
				(String)row.get("id_tipo_veicolo"), 
				(Integer)row.get("numero_ruote"),
				(String)row.get("id_alimentazione"),
				(String)row.get("id_categoria"),
				(String)row.get("id_colore"),
				(String)row.get("id_marca"),
				(String)row.get("anno_prod"),
				(String)row.get("modello")));
		}
	}
	
			
}
