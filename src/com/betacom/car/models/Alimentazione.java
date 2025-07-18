package com.betacom.car.models;

public class Alimentazione {
	
	private String idAlimentazione;
	private String descrizione;
	
	
	public Alimentazione(String idAlimentazione, String descrizione) {
		super();
		this.idAlimentazione = idAlimentazione;
		this.descrizione = descrizione;
	}
	
	
	public String getIdAlimentazione() {
		return idAlimentazione;
	}
	public void setIdAlimentazione(String idAlimentazione) {
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
		return "Alimentazione [idAlimentazione=" + idAlimentazione + ", descrizione=" + descrizione + "]";
	}
	
}

