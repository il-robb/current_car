package com.betacom.car.models;

public class Colore {
	
	private Integer idColore;
	private String descrizione;
	
	////////////////////////////////////
	
	public Colore(Integer idColore, String descrizione) {
		super();
		this.idColore = idColore;
		this.descrizione = descrizione;
	}

	////////////////////////////////////
	
	public Integer getIdColore() {
		return idColore;
	}
	public void setIdColore(Integer idColore) {
		this.idColore = idColore;
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
		return "Colore [idColore=" + idColore + ", descrizione=" + descrizione + "]";
	}
	
}
