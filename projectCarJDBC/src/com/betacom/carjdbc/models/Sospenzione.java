package com.betacom.carjdbc.models;

public class Sospenzione {

	private Integer idSpospensione;
	private String descrizione;
	
	
	public Sospenzione() {
		super();
	}


	public Sospenzione(Integer idSpospensione, String descrizione) {
		super();
		this.idSpospensione = idSpospensione;
		this.descrizione = descrizione;
	}


	public Integer getIdSpospensione() {
		return idSpospensione;
	}


	public void setIdSpospensione(Integer idSpospensione) {
		this.idSpospensione = idSpospensione;
	}


	public String getDescrizione() {
		return descrizione;
	}


	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}


	@Override
	public String toString() {
		return "Sospenzione [idSpospensione=" + idSpospensione + ", descrizione=" + descrizione + "]";
	}
	
	
	
}
