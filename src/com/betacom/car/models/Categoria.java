package com.betacom.car.models;

public class Categoria {
	
	private Integer idCategoria;
	private String descrizione;
	
	////////////////////////////////////
	
	public Categoria(Integer idCategoria, String descrizione) {
		super();
		this.idCategoria = idCategoria;
		this.descrizione = descrizione;
	}
	
	////////////////////////////////////
	
	public Integer getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(Integer idAlimentazione) {
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
