package com.betacom.car.process;

import java.util.List;
import java.util.Map;

import com.betacom.car.dao.AlimentazioneDAO;
import com.betacom.car.dao.BiciDAO;
import com.betacom.car.dao.CategoriaDAO;
import com.betacom.car.dao.ColoreDAO;
import com.betacom.car.dao.MacchinaDAO;
import com.betacom.car.dao.MarcaDAO;
import com.betacom.car.dao.MotoDAO;
import com.betacom.car.dao.SospensioneDAO;
import com.betacom.car.dao.VeicoloDAO;
import com.betacom.car.exception.AcademyException;
import com.betacom.car.models.Bici;
import com.betacom.car.models.Macchina;
import com.betacom.car.models.Moto;
import com.betacom.car.models.Veicolo;
import com.betacom.car.singletone.SQLConfiguration;

public class StartVeicolo {
	
	//dao da richiamare
	//dao principali
	VeicoloDAO DAOvei = new VeicoloDAO();
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
	
	
	

	public boolean execute(List<String> params) {
		System.out.println("Begin StartVeicolo");
		boolean rc = true;
		try {
			SQLConfiguration.getInstance().getConnection();
			
			
			
			for (String inp : params) {
				String[] oper = inp.split(":");
				// scan input and dispatch per operation

				switch (oper[0]) {
				case "add":
					metodoAdd(oper[1]);
					break;
				case "update":
					metodoUpdate(oper[1]);
					break;
				case "delete":
					metodoDelete(oper[1]);
					break;
				case "list":
					if(oper.length == 1) {
						metodoList("all");
					}else {
						metodoList(oper[1]);
					}
					break;

				}
			}
		} catch (Exception e) {
			System.out.println("Error found:" + e.getMessage());
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
	
	//////////////////////////////////////////////

	//fatto
	//FUNZIONA
	public void metodoAdd(String parameters) throws Exception {

		String[] valori = parameters.split(",");
		switch (valori[0]) {
		case "macchina" :
			Object[] parteVeicolo = new Object[]{
										valori[0], valori[1], valori[2],
										valori[3], valori[4], valori[5],
										valori[6], valori[7]};
			Object[] parteMacchina = new Object[] {
										valori[8], valori[9], valori[10]};
			DAOvei.insert("veicolo.insert", parteVeicolo, parteMacchina);
			break;
			////////////////////////
			
		case "moto" :
			Object[] parteVeicolo2 = new Object[]{
										valori[0], valori[1], valori[2],
										valori[3], valori[4], valori[5],
										valori[6], valori[7]};
			Object[] parteMoto = new Object[] {
										valori[8], valori[9]};
			DAOvei.insert("veicolo.insert", parteVeicolo2, parteMoto);
			////////////////////////
			
			break;
		case "bici" :
			Object[] parteVeicolo3 = new Object[]{
										valori[0], valori[1], valori[2],
										valori[3], valori[4], valori[5],
										valori[6], valori[7]};
			Object[] parteBici = new Object[] {
										valori[8], valori[9], valori[10]};
			DAOvei.insert("veicolo.insert", parteVeicolo3, parteBici);
			
			break;	
		}
		
	}

	//fatto
	public void metodoUpdate(String parameters) throws Exception {
		
		String[] valori = parameters.split(",");
		Veicolo veicolo = new Veicolo();
		
		switch (valori[0]) {
				case "macchina" :
				Macchina macchina = DAOmac.findById(new Object[] {valori[1]}).orElse(new Macchina());
				veicolo = DAOvei.findById(new Object[] {macchina.getIdVeicolo()}).orElse(new Veicolo());
				//modifico i valori qui su java
				for(int i=2;i<valori.length;i++) {
					String[] daCambiare = valori[i].split("=");
					switch (daCambiare[0]) {
						case "numeroPorte" :
							if(Integer.parseInt(daCambiare[1]) != macchina.getNumeroPorte()) {
								macchina.setNumeroPorte(Integer.parseInt(daCambiare[1]));
							}
							break;
						case "targa" :
							if(daCambiare[1] != macchina.getTarga()) {
								macchina.setTarga(daCambiare[1]);
							}
							break;
						case "cilindrata" :
							if(Integer.parseInt(daCambiare[1]) != macchina.getCilindrata()) {
								macchina.setCilindrata(Integer.parseInt(daCambiare[1]));
							}
							break;
					}	
				}
				//da vedere se funziona solo macchina e basta
				DAOmac.update("macchina.update", new Object[] {macchina});
				break;
				
			case "moto" :
				Moto moto = DAOmot.findById(new Object[] {valori[1]}).orElse(new Moto());
				veicolo = DAOvei.findById(new Object[] {moto.getId_veicolo()}).orElse(new Veicolo());
				//modifico i valori qui su java
				for(int i=2;i<valori.length;i++) {
					String[] daCambiare = valori[i].split("=");
					switch (daCambiare[0]) {
						case "targa" :
							if(daCambiare[1] != moto.getTarga()) {
								moto.setTarga(daCambiare[1]);
							}
							break;
						case "cilindrata" :
							if(Integer.parseInt(daCambiare[1]) != moto.getCilindrata()) {
								moto.setCilindrata(Integer.parseInt(daCambiare[1]));
							}
							break;
					}	
				}
				//da vedere se funziona come si deve
				DAOmot.update("moto.update", new Object[] {moto});
				break;
				
			case "bici" :
				Bici bici = DAObici.findById(new Object[] {valori[1]}).orElse(new Bici());
				veicolo = DAOvei.findById(new Object[] {bici.getIdVeicolo()}).orElse(new Veicolo());
				//modifico i valori qui su java
				for(int i=2;i<valori.length;i++) {
					String[] daCambiare = valori[i].split("=");
					switch (daCambiare[0]) {
						case "numeroMarce" :
							if(Integer.parseInt(daCambiare[1]) != bici.getNumeroMarce()) {
								bici.setNumeroMarce(Integer.parseInt(daCambiare[1]));
							}
							break;
						case "sospensione" :
							if(DAOsos.findID(daCambiare[1]) != bici.getIdSospenzione()) {
								bici.setIdSospenzione(Integer.parseInt(daCambiare[1]));
							}
							break;
						case "pieghevole" :
							if(Boolean.valueOf(daCambiare[1]) != bici.isPieghevole()) {
								bici.setPieghevole(Boolean.valueOf(daCambiare[1]));
							}
							break;
					}	
				}
				//da vedere se funziona
				DAObici.update("bici.update", new Object[] {bici});
				break;
		}
		
		//parte per modificare il veicolo
		for(int i=2;i<valori.length;i++) {
			String[] daCambiare = valori[i].split("=");
			switch (daCambiare[0]) {
				case "numeroRuote" :
					if(Integer.parseInt(daCambiare[1]) != veicolo.getNumeroRuote()) {
						veicolo.setNumeroRuote(Integer.parseInt(daCambiare[1]));
					}
					break;
				case "alimentazione" :
					if(DAOali.findID(daCambiare[1]) != veicolo.getIdAlimentazione()) {
						veicolo.setIdAlimentazione(Integer.parseInt(daCambiare[1]));
					}
					break;
				case "categoria" :
					if(DAOcat.findID(daCambiare[1]) != veicolo.getIdCategoria()) {
						veicolo.setIdCategoria(Integer.parseInt(daCambiare[1]));
					}
					break;
				case "colore" :
					if(DAOcol.findID(daCambiare[1]) != veicolo.getIdColore()) {
						veicolo.setIdColore(Integer.parseInt(daCambiare[1]));
					}
					break;
				case "marca" :
					if(DAOmarca.findID(daCambiare[1]) != veicolo.getIdMarca()) {
						veicolo.setIdMarca(Integer.parseInt(daCambiare[1]));
					}
					break;	
				case "annoProduzione" :
					if(Integer.parseInt(daCambiare[1]) != veicolo.getAnnoProduzione()) {
						veicolo.setAnnoProduzione(Integer.parseInt(daCambiare[1]));
					}
					break;	
				case "modello" :
					if(daCambiare[1] != veicolo.getModello()) {
						veicolo.setModello(daCambiare[1]);
					}
					break;	
			}
		}
		//da vedere se funziona cosi
		DAOvei.update("veicolo.update", new Object[] {veicolo});
		
		
	}
	
	
	//fatto
	//da rivedere
	public void metodoDelete(String parameters) throws Exception {
		
		int id_veicolo = -1;
		String[] valori = parameters.split(",");
		switch (valori[0]) {
			case "macchina" :
				Macchina macchina = DAOmac.findById(new Object[] {valori[1]}).orElse(new Macchina());
				id_veicolo = macchina.getIdVeicolo();
				DAOmac.delete("macchina.delete", new Object[] {valori[1]});
				DAOvei.delete("veicolo.delte", new Object[] {id_veicolo});
				break;
				
			case "moto":
				Moto moto = DAOmot.findById(new Object[] {valori[1]}).orElse(new Moto());
				id_veicolo = moto.getId_veicolo();
				System.out.println("Ciaooo: " + moto.getId_veicolo());
				DAOmot.delete("moto.delete", new Object[] {valori[1]});
				DAOvei.delete("veicolo.delete", new Object[] {id_veicolo});
				break;
				
			case "bici":
				Bici bici = DAObici.findById(new Object[] {valori[1]}).orElse(new Bici());
				id_veicolo = bici.getIdVeicolo();
				DAObici.delete("bici.delete", new Object[] {valori[1]});
				DAOvei.delete("veicolo.delete", new Object[] {id_veicolo});
				break;
				
			default:
				System.out.println("Non trovato il tipo di veicolo da eliminare");
				break;
		}	
		
	}

	
	//fatto
	//FUNZIONA
	//ATTENZIONE AL FIND ALL
	public void metodoList(String parameters) throws Exception {

		switch (parameters) {
			case "macchina":
				List<Macchina> listaMacchine = DAOmac.findAll();
				int num = 1;
				for(Macchina macchina : listaMacchine) {
					System.out.println("Macchina: " + num + ":\n" + macchina.toString());
					num++;
				}
				break;
			case "moto":
				List<Moto> listaMoto = DAOmot.findAll();
				int num2 = 1;
				for(Moto moto : listaMoto) {
					System.out.println("Moto: " + num2 + ":\n" + moto.toString());
					num2++;
				}
				break;
			case "bici":
				List<Bici> listaBici = DAObici.findAll();
				int num3 = 1;
				for(Bici bici : listaBici) {
					System.out.println("Bici: " + num3 + ":\n" + bici.toString());
					num3++;
				}
				break;
			case "all" :
				List<Map<Veicolo, Object>> listaFinale = DAOvei.findAll();
				int num4 = 1;
				for(Map<Veicolo, Object> oggettofinale : listaFinale) {
					System.out.println("Veicolo: " + num4 + ":\n" + oggettofinale.toString());
					num4++;
				}
				break;
		}
		
		
	}




}
