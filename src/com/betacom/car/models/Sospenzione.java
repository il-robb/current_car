package com.betacom.car.models;

public class Sospenzione {

	private Integer idSospensione;
	private String descrizione;
	
	////////////////////////////////////
	
	public Sospenzione(Integer idSospensione, String descrizione) {
		super();
		this.idSospensione = idSospensione;
		this.descrizione = descrizione;
	}
	
	////////////////////////////////////
	
	public Integer getIdSospensione() {
		return idSospensione;
	}
	public void setIdSospensione(Integer idAlimentazione) {
		this.idSospensione = idAlimentazione;
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
		return "Sospensione [idSospensione=" + idSospensione + ", descrizione=" + descrizione + "]";
	}
}

