package com.betacom.carjdbc.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.betacom.carjdbc.models.Veicolo;
import com.betacom.carjdbc.singletone.SQLConfiguration;
import com.betacom.carjdbc.utilities.SQLManager;

public class VeicoloDAO {
 
	private SQLManager db = new SQLManager();
	private MacchinaDAO doaMa = new MacchinaDAO();
	private MotoDAO daoMo = new MotoDAO();
	private BiciDAO daoB = new BiciDAO();
	
	
	public int insert(Object[] paramsV, Object[] paramsS) throws Exception{
		int numero = 0;
		
		String qry = SQLConfiguration.getInstance().getQuery("veicolo.insert");
		System.out.println("Query:" + qry);
		
		
		numero = db.update(qry, paramsV,true);
		
		Object[] paramsM = new Object[4];
		paramsM[0]=numero;
		
		switch (paramsV[0].toString()){
		case "moto": {
			daoMo.insert(paramsM);
			break;
		}
		case "macchina": {
			
			doaMa.insert(paramsM);
			break;
		}
		case "bici": {
		
			daoB.insert(paramsM);
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + paramsV[0].toString());
		}
		
		return numero;
	}

	public int update(Object[] paramsV, Object[] paramsS) throws Exception{
		int numero = 0;
		
		String qry = SQLConfiguration.getInstance().getQuery("veicolo.update");
		System.out.println("Query:" + qry);
		
		
		numero = db.update(qry, paramsV,true);
		
		Object[] paramsM = new Object[4];
		paramsM[0]=numero;
		
		switch (paramsV[0].toString()){
		case "moto": {
			daoMo.update(paramsM);
			break;
		}
		case "macchina": {
			
			doaMa.update(paramsM);
			break;
		}
		case "bici": {
		
			daoB.update(paramsM);
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + paramsV[0].toString());
		}
		
		return numero;
	}

	
	public int delete(Object[] parameters) throws Exception{
		int numero = 0;
		
		String qry = SQLConfiguration.getInstance().getQuery("veicolo.delete");
		System.out.println("Query:" + qry);
		
		numero = db.update(qry, parameters);
		
		return numero;
	}

	
	public List<Map<Veicolo, Object>> findAll() throws Exception{
		
		String qry = SQLConfiguration.getInstance().getQuery("veicolo");
		System.out.println("Query:" + qry);
		
		List<Map<String, Object>> lM = db.list(qry);
	
		List<Veicolo> object = lM.stream()
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
		
		List<Map<Veicolo, Object>> listaFinale = new ArrayList<Map<Veicolo,Object>>();
		
		for(int i= 0; i < object.size(); i++) {
			
			Map<Veicolo, Object> oggettoFinale = new HashMap<Veicolo, Object>();
			
			switch (object.get(i).getTipoVeicolo()) {
				case "macchina":
					Object[] pma = new Object[1];
					pma[1] = object.get(i).getIdVeicolo();
					oggettoFinale.put(object.get(i),doaMa.findById(null));
					break;
				case "bici":
					Object[] pb = new Object[1];
					pb[1] = object.get(i).getIdVeicolo();
					oggettoFinale.put(object.get(i),doaMa.findById(null));
					break;
				case "moto":
					Object[] pmo = new Object[1];
					pmo[1] = object.get(i).getIdVeicolo();
					oggettoFinale.put(object.get(i),doaMa.findById(null));
					break;
				default:
					System.out.println("tipo non esistente");
				}
			
			listaFinale.add(oggettoFinale);
			}
		return listaFinale;
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

	
	public Optional<Object> findById(Object[] parameters) throws Exception{
		
		String qry = SQLConfiguration.getInstance().getQuery("veicolo.byId");
		System.out.println("Query:" + qry);
		
		Map<String, Object> row = db.get(qry, parameters);
		if (row == null)
			return Optional.empty();
		else {
			return Optional.ofNullable(new Veicolo(
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

