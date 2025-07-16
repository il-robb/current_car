package com.betacom.car.models;

public class Moto {

	private Integer idMoto;
	private Integer idVeicolo;
	private String targa;
	private Integer cilindrata;
	
	public Moto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Moto(Integer idMoto, Integer idVeicolo, String targa, Integer cilindrata) {
		super();
		this.idMoto = idMoto;
		this.idVeicolo = idVeicolo;
		this.targa = targa;
		this.cilindrata = cilindrata;
	}

	public Integer getIdMoto() {
		return idMoto;
	}

	public void setIdMoto(Integer idMoto) {
		this.idMoto = idMoto;
	}

	public Integer getIdVeicolo() {
		return idVeicolo;
	}

	public void setIdVeicolo(Integer idVeicolo) {
		this.idVeicolo = idVeicolo;
	}

	public String getTarga() {
		return targa;
	}

	public void setTarga(String targa) {
		this.targa = targa;
	}

	public Integer getCilindrata() {
		return cilindrata;
	}

	public void setCilindrata(Integer cilindrata) {
		this.cilindrata = cilindrata;
	}

	@Override
	public String toString() {
		return "Moto [idMoto=" + idMoto + ", idVeicolo=" + idVeicolo + ", targa=" + targa + ", cilindrata=" + cilindrata
				+ "]";
	}
	
	
}
