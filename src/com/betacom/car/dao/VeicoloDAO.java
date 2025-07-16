package com.betacom.car.dao;

import com.betacom.car.utilities.SQLManager;
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
		
		Object[] paramsM = new Object[4];
		paramsM[0]=numero;
		
		switch (paramsV[0].toString()){
		case "moto": {
			daoMo.insert(paramsM);
			break;
		}
		case "macchina": {
			
			daoM.insert(paramsM);
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
	
	
	public int delete(Object[] parameters) throws Exception{
		int numero = 0;
		
		String qry = SQLConfiguration.getInstance().getQuery("veicolo.delete");
		System.out.println("Query:" + qry);
		
		numero = db.update(qry, parameters);
		
		return numero;
	}
	
	
	public int update(String qryName,Object[] parameters) throws Exception{
		int numero = 0;
		
		String qry = SQLConfiguration.getInstance().getQuery(qryName);
		System.out.println("Query:" + qry);
		
		numero = db.update(qry, parameters);
		
		return numero;
	}
	public List<Veicolo> findAll() throws Exception{
		
		String qry = SQLConfiguration.getInstance().getQuery("veicolo");
		System.out.println("Query:" + qry);
		
		List<Map<String, Object>> lD = db.list(qry);
	

		return lD.stream()
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
	
public Optional<Veicolo>   findById(Object[] parameters) throws Exception{
		
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
