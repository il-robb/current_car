package com.betacom.car.process;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import com.betacom.car.dao.BiciDAO;
import com.betacom.car.dao.MacchinaDAO;
import com.betacom.car.dao.MotoDAO;
import com.betacom.car.dao.UfficiDAO;
import com.betacom.car.dao.VeicoloDAO;

@Component
public class StartVeicolo {
//	VEICOLO
	public final static int TIPO_VEICOLO = 0;
	public final static int NUMERO_RUOTE = 1;
	public final static int TIPO_ALIMENTAZIONE = 2;
	public final static int CATEGORIA = 3;
	public final static int COLORE = 4;
	public final static int MARCA = 5;
	public final static int ANNO_PRODUZIONE = 6;
	public final static int MODELLO = 7;
//	MACCHINA
	public final static int ID_VEICOLO = 0;
	public final static int NUMERO_PORTE = 1;
	public final static int TARGA_MACCHINA = 2;
	public final static int CC_MACCHINA = 3;
//	MOTO
	public final static int TARGA_MOTO = 1;
	public final static int CC_MOTO = 2;

//	BICI
	public final static int MARCE = 1;
	public final static int TIPO_SOSPENSIONE = 2;
	public final static int PIEGHEVOLE = 3;
//	NUMERO PARAMETRI
	public final static int PARAM_MAC = 11;
	public final static int PARAM_MOTO = 10;
	public final static int PARAM_BICI = 11;
//	NUMERO PER VEICOLO
	public final static int MACCHINA=2;
	public final static int MOTO=2;
	public final static int BICI=2;

	@Autowired
	private VeicoloDAO daoV ;
	@Autowired
	private MacchinaDAO daoC ;
	@Autowired
	private BiciDAO daoB;
	@Autowired
	private MotoDAO daoM ;
	
	public boolean execute(List<String> params) {
		System.out.println("Begin StartVeicolo");
		boolean rc = true;
		try {

			for(String inp:params) {
				String[] oper = inp.split(":");

				switch (oper[0]) {
				case "add": {
					Object[] params1 = oper[1].split(",");
					add(params1);
		            break;

				}
				case "update": {
//					Object[] params1 = oper[1].split(",");
//					update(params1);
					break;

				}
				case "delete": {
					Object[] params1 = oper[1].split(",");
					daoV.delete(params1);
					break;

				}
				case "list": {
					break;
				}

				default:
					throw new IllegalArgumentException("Unexpected value: " + oper[0]);
				}
				// scan input and dispatch per operation


			}
		} catch (Exception e) {
			System.out.println ("Error found:" + e.getMessage());
			rc = false;

		}
		return rc;
	}


	private void add(Object[] params1)
	{
		SqlParameterSource paraV = null;
		SqlParameterSource paraM = null;
		try {

			String pattern=  daoV.getElem("select pattern from tipo_veicolo where nome = :tipo_veicolo",
	        		paraM= new MapSqlParameterSource("tipo_veicolo",params1[TIPO_VEICOLO])).toString();
			
	        params1[TIPO_VEICOLO]= daoV.getElem("select tipo from tipo_veicolo where nome = :tipo_veicolo",
	        		paraM= new MapSqlParameterSource("tipo_veicolo",params1[TIPO_VEICOLO].toString()));

	        params1[TIPO_ALIMENTAZIONE]=  daoV.getElem("select id_alimentazione from alimentazione where descrizione = :tipo_veicolo AND id_alimentazione LIKE :pattern",
	        		paraM= new MapSqlParameterSource("tipo_veicolo",params1[TIPO_ALIMENTAZIONE].toString()).addValue("pattern", pattern));

	        params1[CATEGORIA]=  daoV.getElem("select id_categoria from categoria where descrizione = :tipo_veicolo AND id_categoria LIKE :pattern",
	        		paraM= new MapSqlParameterSource("tipo_veicolo",params1[CATEGORIA].toString()).addValue("pattern", pattern));

	        params1[COLORE]= daoV.getElem("select id_colore from colore where descrizione = :tipo_veicolo ",
	        		paraM= new MapSqlParameterSource("tipo_veicolo",params1[COLORE].toString()));

	        params1[MARCA]=  daoV.getElem("select id_marca from marca where descrizione = :tipo_veicolo ",
	        		paraM= new MapSqlParameterSource("tipo_veicolo",params1[MARCA].toString()));

	        paraV = new MapSqlParameterSource("tipo_veicolo",params1[TIPO_VEICOLO])
	        		.addValue("numero_ruote", params1[NUMERO_RUOTE])
	        		.addValue("id_alimentazione", params1[TIPO_ALIMENTAZIONE])
	        		.addValue("id_categoria", params1[CATEGORIA])
	        		.addValue("id_colore", params1[COLORE])
	        		.addValue("id_marca", params1[MARCA])
	        		.addValue("anno_prod", params1[ANNO_PRODUZIONE])
	        		.addValue("modello", params1[MODELLO]);

	        int key = daoV.addVeicolo(paraV);

			switch (params1[0].toString()){
			case "2": {
				paraM = new MapSqlParameterSource("targa",params1[8]).addValue("cilindrata", params1[9]).addValue("id_veicolo", key);
				daoM.addMoto(paraM);
				break;
			}
			case "1": {
				paraM = new MapSqlParameterSource("n_porte",params1[8]).addValue("targa", params1[9]).addValue("cilindrata", params1[10]).addValue("id_veicolo", key);
				daoC.addMacchina(paraM);
				break;
			}
			case "3": {
				if(params1[10].equals("true")) {
					params1[10]=1;
				} else {
					params1[10]=0;
				}

				 params1[9]=  daoV.getElem("select id_sospensione from sospensione where descrizione = :tipo_veicolo ",
		        		paraM= new MapSqlParameterSource("tipo_veicolo",params1[9].toString()));


				paraM = new MapSqlParameterSource("n_marce",params1[8]).addValue("id_sospensione", params1[9]).addValue("pieghevole", params1[10]).addValue("id_veicolo", key);
				daoB.addBici(paraM);
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + params1[0].toString());
			}
			System.out.println("pre");



			}
		catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}

		return ;

	}

//	private void update(Object[] params1) throws Exception
//	{
//		Map<String,Object> mapM = new HashMap<String, Object>();
//		String qry = SQLConfiguration.getInstance().getQuery("veicolo.byId");
//		String qryV = "";
//		String qryM = "";
//		int trigV=0;
//		int trigM=0;
//
//		Map<String,Object> mapV = db.get(qry,new Object[] {params1[1]});
//
//		for(int i=0;i<params1.length;i++)System.out.println(params1[i].toString());
//
//		switch (params1[0].toString()) {
//		case "moto": {
//			qry = SQLConfiguration.getInstance().getQuery("moto.byId");
//			mapM = db.get(qry,new Object[] {params1[1]});
//			break;
//		}
//		case "macchina": {
//			qry = SQLConfiguration.getInstance().getQuery("macchina.byId");
//			mapM = db.get(qry,new Object[] {params1[1]});
//			break;
//		}
//		case "bici": {
//			qry = SQLConfiguration.getInstance().getQuery("bici.byId");
//			mapM = db.get(qry,new Object[] {params1[1]});
//			break;
//		}
//		default:
//			throw new IllegalArgumentException("Unexpected veicolo: " + params1[1]);
//		}
//
//		for(int i=2;i<params1.length;i++)
//		{
//			String key = params1[i].toString().split("=")[0] ;
//			Object value = params1[i].toString().split("=")[1] ;
//
//			System.out.println("chiave "+key+ " valore "+value.toString());
//
//			qry = SQLConfiguration.getInstance().getQuery("pattern_tipo_veicolo.getId");
//			Object pattern=  db.get(qry,new Object[] {mapV.get("tipo_veicolo").toString()}).get("pattern");
//
//			if(mapV.containsKey(key))
//			{
//				if(trigV==1)qryV=qryV+", ";
//				else trigV++;
//
//				if(key.equals("id_alimentazione"))
//				{
//					qry = SQLConfiguration.getInstance().getQuery("alimentazione.getId");
//					qryV = qryV +key+ "= \""+ db.get(qry,new Object[] {value.toString(),pattern}).get("id_alimentazione")+"\"";
//				}
//				else if(key.equals("id_categoria"))
//				{
//					qry = SQLConfiguration.getInstance().getQuery("categoria.getId");
//					qryV = qryV +key+ "=\""+ db.get(qry,new Object[] {value.toString(),pattern}).get("id_categoria")+"\"";
//				}
//				else if(key.equals("id_colore"))
//				{
//					qry = SQLConfiguration.getInstance().getQuery("colore.getId");
//					qryV = qryV +key+ "=\""+ db.get(qry,new Object[] {value.toString()}).get("id_colore")+"\"";
//				}
//				else if(key.equals("id_marca"))
//				{
//					qry = SQLConfiguration.getInstance().getQuery("marca.getId");
//					qryV = qryV +key+ "=\""+ db.get(qry,new Object[] {value.toString()}).get("id_marca")+"\"";
//				}
//				else qryV = qryV +key+ "=\""+ value+"\"";
//			}
//			else if(mapM.containsKey(key))
//				{
//				if(trigM==1)qryM=qryM+", ";
//				else trigM++;
//				if(key.equals("id_sospensione"))
//				{
//					qry = SQLConfiguration.getInstance().getQuery("sospensione.getId");
//					qryM = qryM +key+ "=\""+ db.get(qry,new Object[] {value.toString()}).get("id_sospensione")+"\"";
//				}
//				else qryM = qryM +key+ "=\""+ value+"\"";
//				}
//				else {
//					System.out.println("valore "+value.toString() + " di "+key+" non è valido");
//					return ;
//				}
//		}
//
//			if(qryV!="") {
//				qryV="update veicolo set "+qryV + " where id_veicolo= "+params1[1];
//				System.out.println(qryV);
//				System.out.println(qryM);
//				db.update(qryV,new Object[] {});
//			}
//
//			System.out.println(qryM);
//
//			switch (params1[0].toString()) {
//			case "moto": {
//				if(qryM!="") {
//					qryM="update moto set "+qryM + " where id_veicolo= "+params1[1];}
//
//				break;
//			}
//			case "macchina": {
//				if(qryM!="") {
//				qryM="update moto set "+qryM + " where id_veicolo= "+params1[1];}
//
//				break;
//			}
//			case "bici": {
//				if(qryM!="") {
//				qryM="update bici set "+qryM + " where id_veicolo= "+params1[1];
//				}
//				break;
//			}
//			default:
//				throw new IllegalArgumentException("Unexpected veicolo: " + params1[1]);
//			}
//			System.out.println(qryM);
//			db.update(qryM,new Object[] {});
//
//
//
//
//	}




}
