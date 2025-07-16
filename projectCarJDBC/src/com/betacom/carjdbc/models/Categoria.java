package com.betacom.carjdbc.models;

public class Categoria {
	 
	private Integer idCategoria;
	private String descrizione;
	
	
	public Categoria() {
		super();
	}


	public Categoria(Integer idCategoria, String descrizione) {
		super();
		this.idCategoria = idCategoria;
		this.descrizione = descrizione;
	}


	public Integer getIdCategoria() {
		return idCategoria;
	}


	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}


	public String getDescrizione() {
		return descrizione;
	}


	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}


	@Override
	public String toString() {
		return "Categoria [idCategoria=" + idCategoria + ", descrizione=" + descrizione + "]";
	}
	
	
	
}
