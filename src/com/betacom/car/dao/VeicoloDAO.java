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
		
		System.out.println("insert veicolo ok "+numero);
		
		paramsS[0]=numero;
		
		switch (Integer.parseInt(paramsV[0].toString())){
		case 2: {
			daoMo.insert(paramsS);
			break;
		}
		case 1: {
			
			daoM.insert(paramsS);
			break;
		}
		case 3: {
		
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
		
		switch (Integer.parseInt(parameters[0].toString())) {
		case 2: {//moto
			daoMo.delete(new Object[] {parameters[1]});
			break;
		}
		case 1: {//macchina
			daoM.delete(new Object[] {parameters[1]});
			break;
		}
		case 3: {//bici
			daoB.delete(new Object[] {parameters[1]});
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected veicolo: " + parameters[1]);
		}
		
		String qry = SQLConfiguration.getInstance().getQuery("veicolo.delete");
		System.out.println("Query:" + qry);
		
		numero = db.update(qry,new Object[] {parameters[1]});
		
		return numero;
	}
	
	
	public int update(String qryname,Object[] parameters) throws Exception{
		int numero = 0;
		
		String qry = SQLConfiguration.getInstance().getQuery(qryname);
		System.out.println("Query:" + qry);
		
		numero = db.update(qry , parameters);
		
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
						(String)row.get("id_alimentazione"),
						(String)row.get("id_categoria"),
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
				(String)row.get("id_alimentazione"),
				(String)row.get("id_categoria"),
				(Integer)row.get("id_colore"),
				(Integer)row.get("id_marca"),
				(Integer)row.get("anno_prod"),
				(String)row.get("modello")));
		}
	}
	
			
}
