package com.betacom.car.models;

public class Marca {

	private Integer idMarca;
	private String descrizione;
	
	////////////////////////////////////
	
	public Marca(Integer idMarca, String descrizione) {
		super();
		this.idMarca = idMarca;
		this.descrizione = descrizione;
	}

	////////////////////////////////////
	
	public Integer getIdMarca() {
		return idMarca;
	}
	public void setIdMarca(Integer idMarca) {
		this.idMarca = idMarca;
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
		return "Marca [idMarca=" + idMarca + ", descrizione=" + descrizione + "]";
	}

}
