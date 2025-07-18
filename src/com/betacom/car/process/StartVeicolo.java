package com.betacom.car.process;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.betacom.car.dao.BiciDAO;
import com.betacom.car.dao.MacchinaDAO;
import com.betacom.car.dao.MotoDAO;
import com.betacom.car.dao.VeicoloDAO;
import com.betacom.car.exception.AcademyException;
import com.betacom.car.models.Bici;
import com.betacom.car.models.Macchina;
import com.betacom.car.models.Moto;
import com.betacom.car.models.Veicolo;
import com.betacom.car.singletone.SQLConfiguration;
import com.betacom.car.utilities.SQLManager;


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
	
	private BiciDAO daoB = new BiciDAO();
	private MotoDAO daoMo = new MotoDAO();
	private MacchinaDAO daoM = new MacchinaDAO();
	private VeicoloDAO daoV = new VeicoloDAO();
			
	public boolean execute(List<String> params) {
		System.out.println("Begin StartVeicolo");
		boolean rc = true;
	
			try {
				SQLConfiguration.getInstance().getConnection();
				SQLManager db = new SQLManager();
				String qry;
				for(String inp:params) {
					String[] oper = inp.split(":");
					
					switch (oper[0]) {
					case "add": {
						Object[] params1 = oper[1].split(",");
						List<Object[]> l = add(params1,db);
			            daoV.insert(l.get(0), l.get(1));
			            break;
						
					}
					case "update": {
						Object[] params1 = oper[1].split(",");
						update(params1,db);
						break;
						
					}
					case "delete": {
						Object[] params1 = oper[1].split(",");
						daoV.delete(params1);
						break;
					}
					case "list": {
						String params1 = oper[1];
						list(params1,db);
							
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
		try {
			SQLConfiguration.getInstance().closeConnection();
		} catch (AcademyException e) {
			System.out.println("Error in close connection:" + e.getMessage());
		}
		System.out.println("Connection is closed....");
		return rc;
	}
	
	private List<Object[]>add(Object[] params1,SQLManager db)
	{
		Object[] paramsV = new Object[8];
		Object[] paramsM = new Object[4];
		String qry;
		int k=1;
		try {
		
			for(int i = 0;i<8;i++)
			{
				paramsV[i]= params1[i];
			}
			
			switch (paramsV[0].toString()){
			case "moto": {
				
				for(int i = 8;i<10;i++)
				{
					System.out.println(params1[i].toString());
					paramsM[k]= params1[i];
					k++;
				}
				break;
			}
			case "macchina": {
				for(int i = 8;i<11;i++)
				{
					paramsM[k]= params1[i];
					k++;
				}
				break;
			}
			case "bici": {
			
			for(int i = 8;i<11;i++)
			{
				paramsM[k]= params1[i];
				k++;
			}
				if(paramsM[PIEGHEVOLE].equals("true"))paramsM[PIEGHEVOLE]=1;else paramsM[PIEGHEVOLE]=0;
				qry = SQLConfiguration.getInstance().getQuery("sospensione.getId");
	            paramsM[TIPO_SOSPENSIONE]=  db.get(qry,new Object[] {paramsM[TIPO_SOSPENSIONE].toString()}).get("id_sospensione");
	            System.out.println(paramsM[2]);
	           
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + paramsV[0].toString());
			}
			System.out.println("pre");
			qry = SQLConfiguration.getInstance().getQuery("tipo_veicolo.getId");
			System.out.println("query "+qry);
			
			
	        Object pattern=  db.get(qry,new Object[] {paramsV[TIPO_VEICOLO].toString()}).get("pattern");
	        paramsV[TIPO_VEICOLO]=  db.get(qry,new Object[] {paramsV[TIPO_VEICOLO].toString()}).get("tipo");
			qry = SQLConfiguration.getInstance().getQuery("alimentazione.getId");
	        paramsV[TIPO_ALIMENTAZIONE]=  db.get(qry,new Object[] {paramsV[TIPO_ALIMENTAZIONE].toString(),pattern}).get("id_alimentazione");
	        qry = SQLConfiguration.getInstance().getQuery("categoria.getId");
	        paramsV[CATEGORIA]=  db.get(qry,new Object[] {paramsV[CATEGORIA].toString(),pattern}).get("id_categoria");
	        qry = SQLConfiguration.getInstance().getQuery("colore.getId");
	        paramsV[COLORE]=  db.get(qry,new Object[] {paramsV[COLORE].toString()}).get("id_colore");
	        qry = SQLConfiguration.getInstance().getQuery("marca.getId");
	        paramsV[MARCA]=  db.get(qry,new Object[] {paramsV[MARCA].toString()}).get("id_marca");
			} 
		catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
		List<Object[]> l = new ArrayList<Object[]>();
		l.add(paramsV);
		l.add(paramsM);
		
		return l;
        
	}
	
	private void update(Object[] params1,SQLManager db) throws Exception
	{
		Map<String,Object> mapM = new HashMap<String, Object>();
		String qry = SQLConfiguration.getInstance().getQuery("veicolo.byId");
		String qryV = "";
		String qryM = "";
		int trigV=0;
		int trigM=0;
		
		Map<String,Object> mapV = db.get(qry,new Object[] {params1[1]});
		
		for(int i=0;i<params1.length;i++)System.out.println(params1[i].toString());
		
		switch (params1[0].toString()) {
		case "moto": {
			qry = SQLConfiguration.getInstance().getQuery("moto.byId");
			mapM = db.get(qry,new Object[] {params1[1]});
			break;
		}
		case "macchina": {
			qry = SQLConfiguration.getInstance().getQuery("macchina.byId");
			mapM = db.get(qry,new Object[] {params1[1]});
			break;
		}
		case "bici": {
			qry = SQLConfiguration.getInstance().getQuery("bici.byId");
			mapM = db.get(qry,new Object[] {params1[1]});
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected veicolo: " + params1[1]);
		}
		
		for(int i=2;i<params1.length;i++)
		{
			String key = params1[i].toString().split("=")[0] ;
			Object value = params1[i].toString().split("=")[1] ;
			
			System.out.println("chiave "+key+ " valore "+value.toString());
			
			qry = SQLConfiguration.getInstance().getQuery("pattern_tipo_veicolo.getId");
			Object pattern=  db.get(qry,new Object[] {mapV.get("tipo_veicolo").toString()}).get("pattern");
			
			if(mapV.containsKey(key))
			{	
				if(trigV==1)qryV=qryV+", ";
				else trigV++;
				
				if(key.equals("id_alimentazione"))
				{
					qry = SQLConfiguration.getInstance().getQuery("alimentazione.getId");
					qryV = qryV +key+ "= \""+ db.get(qry,new Object[] {value.toString(),pattern}).get("id_alimentazione")+"\"";
				}
				else if(key.equals("id_categoria"))
				{
					qry = SQLConfiguration.getInstance().getQuery("categoria.getId");
					qryV = qryV +key+ "=\""+ db.get(qry,new Object[] {value.toString(),pattern}).get("id_categoria")+"\"";
				}
				else if(key.equals("id_colore"))
				{
					qry = SQLConfiguration.getInstance().getQuery("colore.getId");
					qryV = qryV +key+ "=\""+ db.get(qry,new Object[] {value.toString()}).get("id_colore")+"\"";
				}
				else if(key.equals("id_marca"))
				{
					qry = SQLConfiguration.getInstance().getQuery("marca.getId");
					qryV = qryV +key+ "=\""+ db.get(qry,new Object[] {value.toString()}).get("id_marca")+"\"";
				}
				else qryV = qryV +key+ "=\""+ value+"\"";
			}
			else if(mapM.containsKey(key))
				{
				if(trigM==1)qryM=qryM+", ";
				else trigM++;
				if(key.equals("id_sospensione"))
				{
					qry = SQLConfiguration.getInstance().getQuery("sospensione.getId");
					qryM = qryM +key+ "=\""+ db.get(qry,new Object[] {value.toString()}).get("id_sospensione")+"\"";
				}
				else qryM = qryM +key+ "=\""+ value+"\"";
				}
				else {
					System.out.println("valore "+value.toString() + " di "+key+" non è valido");
					return ;
				}
		}
		
			if(qryV!="") {
				qryV="update veicolo set "+qryV + " where id_veicolo= "+params1[1];
				System.out.println(qryV);
				System.out.println(qryM);
				db.update(qryV,new Object[] {});
			}
			
			System.out.println(qryM);
			switch (params1[0].toString()) {
			case "moto": {
				if(qryM!="") {
					qryM="update moto set "+qryM + " where id_veicolo= "+params1[1];}
					
				break;
			}
			case "macchina": {
				if(qryM!="") {
				qryM="update moto set "+qryM + " where id_veicolo= "+params1[1];}
				
				break;
			}
			case "bici": {
				if(qryM!="") {
				qryM="update bici set "+qryM + " where id_veicolo= "+params1[1];
				}
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected veicolo: " + params1[1]);
			}
			System.out.println(qryM);
			db.update(qryM,new Object[] {});
		
		
		
		
	}
	
	private void list(String params,SQLManager db ) throws Exception {
		String[] s = params.split(",");
		
		
		switch (s[0]) {
		case "all": {
			List<Veicolo> lV = daoV.findAll();
			for (Veicolo row : lV) {
				System.out.println(row);
				if(row.getTipoVeicolo().equals("macchina")) {
					Macchina mC = daoM.findById(new Object[] {row.getIdVeicolo()}).orElse(new Macchina());
					System.out.println(mC.toString());
				}
				else if(row.getTipoVeicolo().equals("moto")) {
					Moto mM =	daoMo.findById(new Object[] {row.getIdVeicolo()}).orElse(new Moto());
					System.out.println(mM.toString());
				}
				else if(row.getTipoVeicolo().equals("bici")) {
					Bici mB =	daoB.findById(new Object[] {row.getIdVeicolo()}).orElse(new Bici());
					System.out.println(mB.toString());
				}
			}
			break;
		}
		
		default:
//			Map<String,Object> mapM = new HashMap<String, Object>();
//			String qry = SQLConfiguration.getInstance().getQuery("veicolo.byId");
//			String qryV = "";
//			String qryM = "";
//			int trigV=0;
//			int trigM=0;
//			
//			Map<String,Object> mapV = db.get(qry,new Object[] {params[1]});
			throw new IllegalArgumentException("Unexpected veicolo: " + params);
		}
	}
	
		
		
		
		
		
		
}
