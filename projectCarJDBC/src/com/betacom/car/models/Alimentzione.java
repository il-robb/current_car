package com.betacom.car.models;

public class Alimentzione {

	private Integer idAlimentazione;
	private String descrizione;
	
	
	public Alimentzione() {
		super();
	}


	public Alimentzione(Integer idALimentazione, String descrizione) {
		super();
		this.idAlimentazione = idALimentazione;
		this.descrizione = descrizione;
	}


	public Integer getIdAlimentazione() {
		return idAlimentazione;
	}


	public void setIdAlimentazione(Integer idAlimentazione) {
		this.idAlimentazione = idAlimentazione;
	}


	public String getDescrizione() {
		return descrizione;
	}


	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}


	@Override
	public String toString() {
		return "Alimentzione [idAlimentazione=" + idAlimentazione + ", descrizione=" + descrizione + "]";
	}
	
	 
	
}
