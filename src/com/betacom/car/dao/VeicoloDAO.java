package com.betacom.car.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.betacom.car.models.Veicolo;
import com.betacom.car.singletone.SQLConfiguration;
import com.betacom.car.utilities.SQLManager;

public class VeicoloDAO {

	private SQLManager db = new SQLManager();

	MacchinaDAO DAOmac = new MacchinaDAO();
	MotoDAO DAOmot = new MotoDAO();
	BiciDAO DAObici = new BiciDAO();
	//per i veicoli
	AlimentazioneDAO DAOali = new AlimentazioneDAO();
	CategoriaDAO DAOcat = new CategoriaDAO();
	ColoreDAO DAOcol = new ColoreDAO();
	MarcaDAO DAOmarca = new MarcaDAO();
	//solo per bici
	SospensioneDAO DAOsos = new SospensioneDAO();
	
	//find all dei veicoli
	//bisogna mettere che filtra e cerca anche la seconda parte (bici,moto,macchina)
	public List<Map<Veicolo, Object>> findAll() throws Exception {
		MacchinaDAO DAOmac = new MacchinaDAO();
		MotoDAO DAOmot = new MotoDAO();
		BiciDAO DAObici = new BiciDAO();

		String qry = SQLConfiguration.getInstance().getQuery("veicoli");
		System.out.println("Query:" + qry);

		List<Map<String, Object>> lD = db.list(qry);

		
		List<Veicolo> veicoli = lD.stream()
									.map(row -> new Veicolo(
											(Integer) row.get("id_veicolo"), 
											(String) row.get("tipo_veicolo"),
											(Integer) row.get("numero_ruote"), 
											(Integer) row.get("id_alimentazione"),
											(Integer) row.get("id_categoria"), 
											(Integer) row.get("id_colore"),
											(Integer) row.get("id_marca"), 
											(Integer) row.get("anno_prod"), 
											(String) row.get("modello"))).collect(Collectors.toList());
		
		List<Map<Veicolo, Object>> listaFinale = new ArrayList<Map<Veicolo,Object>>();
		
		for (int i=0; i<veicoli.size();i++) {
			
			Map<Veicolo, Object> oggettoFinale = new HashMap<Veicolo, Object>();
			
			switch (veicoli.get(i).getTipoVeicolo()) {
			
				case "macchina" : 
					Object[] parameter = new Object[1];
					parameter[0] = veicoli.get(i).getIdVeicolo();
					oggettoFinale.put(veicoli.get(i), DAOmac.findById(parameter));
					break;
				case "moto" : 
					Object[] parameter2 = new Object[1];
					parameter2[0] = veicoli.get(i).getIdVeicolo(); 
					oggettoFinale.put(veicoli.get(i),DAOmot.findById(parameter2));
					break;
				case "bici" : 
					Object[] parameter3 = new Object[1];
					parameter3[0] = veicoli.get(i).getIdVeicolo(); 
					oggettoFinale.put(veicoli.get(i),DAObici.findById(parameter3));
					break;
				default:
					System.out.println("Tipo di veicolo non trovato");
			}
			
			listaFinale.add(oggettoFinale);
				
		}
		return listaFinale;
	}
	
	//trova solo la parte veicolo, prima delle differenzazioni
	public Optional<Veicolo> findById(Object[] parameters) throws Exception{

        String qry = SQLConfiguration.getInstance().getQuery("veicolo.byId");
        System.out.println("Query:" + qry);

        Map<String, Object> row = db.get(qry, parameters);
        if (row == null)
            return Optional.empty();
        else {
            return  Optional.ofNullable(new Veicolo(
            		(Integer) row.get("id_veicolo"), 
					(String) row.get("tipo_veicolo"),
					(Integer) row.get("numero_ruote"), 
					(Integer) row.get("id_alimentazione"),
					(Integer) row.get("id_categoria"), 
					(Integer) row.get("id_colore"),
					(Integer) row.get("id_marca"), 
					(Integer) row.get("anno_prod"), 
					(String) row.get("modello")));
        }
    }
	
	
	//nel momento in cui richiamo questo devo inserire un insert del tipo di veicolo
	public int insert(String qryName, Object[] parameters, Object[] parametersInterni) throws Exception {
		//id del veicolo inserito
		int numero = 0;
		
		parameters[2] = DAOali.findID((String) parameters[2]);
		parameters[3] = DAOcat.findID((String) parameters[3]);
		parameters[4] = DAOcol.findID((String) parameters[4]);
		parameters[5] = DAOmarca.findID((String) parameters[5]);
		
		
		String qry = SQLConfiguration.getInstance().getQuery(qryName);
		System.out.println("Query: " + qry);
		
		numero = db.update(qry, parameters, true);
		
		//qui sotto metto l'insert del tipo di veicolo
		switch (parameters[0].toString()) {
		case "macchina":
			Object[] salvaMacchina = new Object[] {
										numero, parametersInterni[0],
										parametersInterni[1], parametersInterni[2]};
			DAOmac.insert("macchina.insert", salvaMacchina);
			break;
		case "moto":
			Object[] salvaMoto = new Object[] {
					numero, parametersInterni[0],parametersInterni[1]};
			DAOmot.insert("moto.insert", salvaMoto);
			break;
		case "bici":
			Object[] salvaBici = new Object[] {
					numero, parametersInterni[0],
					DAOsos.findID((String) parametersInterni[1]), Boolean.valueOf((String)parametersInterni[2])};
			DAObici.insert("bici.insert", salvaBici);
			break;	
		}
		return numero;
	}
	//nel momento in cui richiamo questo devo inserire un update del tipo di veicolo
	public int update(String qryName, Object[] parameters) throws Exception {
		int numero = 0;
		
		String qry = SQLConfiguration.getInstance().getQuery(qryName);
		System.out.println("Query: " + qry);
		
		numero = db.update(qry, parameters);
		
		return numero;
	}
	
	//questo sara il primo delete utilizzato, dopo elimino l'altra parte
	public int delete(String qryName, Object[] parameters) throws Exception {
		int numero = 0;
		
		String qry = SQLConfiguration.getInstance().getQuery(qryName);
		System.out.println("Query: " + qry);
		
		numero = db.update(qry, parameters);
		
		return numero;
	}
	
}
