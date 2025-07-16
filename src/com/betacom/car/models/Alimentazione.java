package com.betacom.car.models;

public class Alimentazione {
	
	private Integer idAlimentazione;
	private String descrizione;
	
	////////////////////////////////////
	
	public Alimentazione(Integer idAlimentazione, String descrizione) {
		super();
		this.idAlimentazione = idAlimentazione;
		this.descrizione = descrizione;
	}
	
	////////////////////////////////////
	
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

	////////////////////////////////////
	
	@Override
	public String toString() {
		return "Alimentazione [idAlimentazione=" + idAlimentazione + ", descrizione=" + descrizione + "]";
	}
	
}

