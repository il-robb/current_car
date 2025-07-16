package com.betacom.carjdbc.models;

public class Macchina {
	
	
	private Integer idMacchina;
	private Integer idVeicolo;
	private Integer nPorte;
	private String targa;
	private Integer cilindrata;
	
	
	public Macchina() {
		super();
	}


	public Macchina(Integer idMacchina, Integer idVeicolo, Integer nPorte, String targa, Integer cilindrata) {
		super();
		this.idMacchina = idMacchina;
		this.idVeicolo = idVeicolo;
		this.nPorte = nPorte;
		this.targa = targa;
		this.cilindrata = cilindrata;
	}


	public Integer getIdMacchina() {
		return idMacchina;
	}


	public void setIdMacchina(Integer idMacchina) {
		this.idMacchina = idMacchina;
	}


	public Integer getIdVeicolo() {
		return idVeicolo;
	}


	public void setIdVeicolo(Integer idVeicolo) {
		this.idVeicolo = idVeicolo;
	}


	public Integer getnPorte() {
		return nPorte;
	}


	public void setnPorte(Integer nPorte) {
		this.nPorte = nPorte;
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
		return "Macchina [idMacchina=" + idMacchina + ", idVeicolo=" + idVeicolo + ", nPorte=" + nPorte + ", targa="
				+ targa + ", cilindrata=" + cilindrata + "]";
	}
	
	
}
