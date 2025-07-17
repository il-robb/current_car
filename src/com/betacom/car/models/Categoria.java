package com.betacom.car.models;

public class Categoria {
	
	private String idCategoria;
	private String descrizione;
	
	////////////////////////////////////
	
	public Categoria(String idCategoria, String descrizione) {
		super();
		this.idCategoria = idCategoria;
		this.descrizione = descrizione;
	}
	
	////////////////////////////////////
	
	public String getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(String idAlimentazione) {
		this.idCategoria = idAlimentazione;
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
		return "Categoria [idCategoria=" + idCategoria + ", descrizione=" + descrizione + "]";
	}
	
}
