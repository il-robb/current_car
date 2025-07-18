package com.betacom.car.models;

public class TipoVeicolo {

	private Integer id;
	private String descrizione;
	private String pattern;
	
	
	public TipoVeicolo() {
		super();
	}


	public TipoVeicolo(Integer id, String descrizione, String pattern) {
		super();
		this.id = id;
		this.descrizione = descrizione;
		this.pattern = pattern;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getDescrizione() {
		return descrizione;
	}


	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}


	public String getPattern() {
		return pattern;
	}


	public void setPattern(String pattern) {
		this.pattern = pattern;
	}


	@Override
	public String toString() {
		return "tipoVeicolo [id=" + id + ", descrizione=" + descrizione + ", pattern=" + pattern + "]";
	}
	
	
}
