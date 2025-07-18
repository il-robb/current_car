package com.betacom.car.process;

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
				Object[] params1 = oper[1].split(",");
				switch (oper[0]) {
				case "add": {

					Object[] paramsV = new Object[8];
					Object[] paramsM = new Object[4];
					int k=1;

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
							System.out.println(paramsM[k].toString());
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
					if (paramsM[3].equals("true")) {
						paramsM[3] = 1;
					}
					else {
						paramsM[3] = 0;
					}
						
						qry = SQLConfiguration.getInstance().getQuery("sospensione.getId");
			            paramsM[TIPO_SOSPENSIONE]=  db.get(qry,new Object[] {paramsM[TIPO_SOSPENSIONE].toString()}).get("id_sospensione");
			            
			           
						break;
					}
					default:
						throw new IllegalArgumentException("Unexpected value: " + paramsV[0].toString());
					}
					qry = SQLConfiguration.getInstance().getQuery("tipo_veicolo.getId");
		            Object pattern =  db.get(qry,new Object[] {paramsV[TIPO_VEICOLO].toString()}).get("pattern");
					paramsV[TIPO_VEICOLO]=  db.get(qry,new Object[] {paramsV[TIPO_VEICOLO].toString()}).get("id");
		            System.out.println("tipo veicolo ok");
					qry = SQLConfiguration.getInstance().getQuery("alimentazione.getId");
		            paramsV[TIPO_ALIMENTAZIONE]=  db.get(qry,new Object[] {paramsV[TIPO_ALIMENTAZIONE].toString(),pattern.toString()}).get("id_alimentazione");
		            System.out.println("alim ok");
		            qry = SQLConfiguration.getInstance().getQuery("categoria.getId");
		            paramsV[CATEGORIA]=  db.get(qry,new Object[] {paramsV[CATEGORIA].toString(),pattern.toString()}).get("id_categoria");
		            System.out.println("categoria ok");
		            qry = SQLConfiguration.getInstance().getQuery("colore.getId");
		            paramsV[COLORE]=  db.get(qry,new Object[] {paramsV[COLORE].toString()}).get("id_colore");
		            System.out.println("colore ok");
		            qry = SQLConfiguration.getInstance().getQuery("marca.getId");
		            paramsV[MARCA]=  db.get(qry,new Object[] {paramsV[MARCA].toString()}).get("id_marca");
		            System.out.println("marca ok");
		            
		            daoV.insert(paramsV, paramsM);
					break;
				}
				case "update": {
					switch (params1[0].toString()) {
					case "moto": {
						daoMo.update(new Object[] {params1[1]});
						break;
					}
					case "macchina": {
						daoM.update(new Object[] {params1[1]});
						break;
					}
					case "bici": {
						daoB.update(new Object[] {params1[1]});
						break;
					}
					default:
						throw new IllegalArgumentException("Unexpected veicolo: " + params1[1]);
					}
				}
				case "delete": {
					
					daoV.delete(params1);
					
					break;
				}
				case "list": {
					
					switch (params1[0].toString()) {
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
					case "": {
						
						break;
					}
					case "-": {
						
						break;
					}
					default:
						throw new IllegalArgumentException("Unexpected veicolo: " + params1[1]);
					}
					
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
		
		
		
		
		
		
}
