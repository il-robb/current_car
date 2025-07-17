package com.betacom.car.dao;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.betacom.car.exception.AcademyException;
import com.betacom.car.models.Bici;
import com.betacom.car.singletone.SQLConfiguration;
import com.betacom.car.utilities.SQLManager;

public class BiciDAO {
	
	private SQLManager  db = new SQLManager(); 
	
	//richiamato per cercare solo la parte delle bici
	public List<Bici> findAll() throws AcademyException {
		
		String qry = SQLConfiguration.getInstance().getQuery("bici");
		System.out.println("Query: " + qry);
		
		List<Map<String, Object>> lM = db.list(qry);
		
		return lM.stream()
				.map(row -> new Bici(
						(Integer)row.get("id_bici"),
						(Integer)row.get("id_veicolo"),
						(Integer)row.get("n_marce"),
						(Integer)row.get("id_sospensione"),
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
                    (Integer)row.get("id_sospensione"),
                    (Boolean)row.get("pieghevole")));
        }
    }
	
	public int insert(String qryName, Object[] parameters) throws Exception {
		int numero = 0;
		
		String qry = SQLConfiguration.getInstance().getQuery(qryName);
		System.out.println("Query: " + qry);
		
		numero = db.update(qry, parameters, true);
		
		return numero;
	}
	public int update(String qryName, Object[] parameters) throws Exception {
		int numero = 0;
		
		String qry = SQLConfiguration.getInstance().getQuery(qryName);
		System.out.println("Query: " + qry);
		
		numero = db.update(qry, parameters);
		
		return numero;
	}
	public int delete(String qryName, Object[] parameters) throws Exception {
		
		int numero = 0;
		
		String qry = SQLConfiguration.getInstance().getQuery(qryName);
		System.out.println("Query: " + qry);
		
		numero = db.update(qry, parameters);
	
		return numero;
	}
	
}
