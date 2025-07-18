package com.betacom.car.models;

public class Colore {
	
	private Integer idColore;
	private String descrizione;
	private String esadecimale;
	
	////////////////////////////////////
	
	public Colore(Integer idColore, String descrizione, String esadecimale) {
		super();
		this.idColore = idColore;
		this.descrizione = descrizione;
		this.esadecimale = esadecimale;
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

	public String getEsadecimale() {
		return esadecimale;
	}
	public void setEsadecimale(String esadecimale) {
		this.esadecimale = esadecimale;
	}

	////////////////////////////////////
	
	@Override
	public String toString() {
		return "idColore=" + idColore + ", descrizione=" + descrizione + ", esadecimale=" + esadecimale;
	}
	
}

