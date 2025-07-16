package com.betacom.carjdbc.models;

public class Veicolo {
 
	private Integer idVeicolo;
	private String tipoVeicolo;
	private Integer nRuote;
	private Integer idAlimentazione;
	private Integer idCategoria;
	private Integer idColoe;
	private Integer idMarca;
	private Integer annoProd;
	private String modello;
	
	
	public Veicolo() {
		super();
	}


	public Veicolo(Integer idVeicolo, String tipoVeicolo, Integer nRuote, Integer idAlimentazione, Integer idCategoria,
			Integer idColoe, Integer idMarca, Integer annoProd, String modello) {
		super();
		this.idVeicolo = idVeicolo;
		this.tipoVeicolo = tipoVeicolo;
		this.nRuote = nRuote;
		this.idAlimentazione = idAlimentazione;
		this.idCategoria = idCategoria;
		this.idColoe = idColoe;
		this.idMarca = idMarca;
		this.annoProd = annoProd;
		this.modello = modello;
	}


	public Integer getIdVeicolo() {
		return idVeicolo;
	}


	public void setIdVeicolo(Integer idVeicolo) {
		this.idVeicolo = idVeicolo;
	}


	public String getTipoVeicolo() {
		return tipoVeicolo;
	}


	public void setTipoVeicolo(String tipoVeicolo) {
		this.tipoVeicolo = tipoVeicolo;
	}


	public Integer getnRuote() {
		return nRuote;
	}


	public void setnRuote(Integer nRuote) {
		this.nRuote = nRuote;
	}


	public Integer getIdAlimentazione() {
		return idAlimentazione;
	}


	public void setIdAlimentazione(Integer idAlimentazione) {
		this.idAlimentazione = idAlimentazione;
	}


	public Integer getIdCategoria() {
		return idCategoria;
	}


	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}


	public Integer getIdColoe() {
		return idColoe;
	}


	public void setIdColoe(Integer idColoe) {
		this.idColoe = idColoe;
	}


	public Integer getIdMarca() {
		return idMarca;
	}


	public void setIdMarca(Integer idMarca) {
		this.idMarca = idMarca;
	}


	public Integer getAnnoProd() {
		return annoProd;
	}


	public void setAnnoProd(Integer annoProd) {
		this.annoProd = annoProd;
	}


	public String getModello() {
		return modello;
	}


	public void setModello(String modello) {
		this.modello = modello;
	}


	@Override
	public String toString() {
		return "Veicolo [idVeicolo=" + idVeicolo + ", tipoVeicolo=" + tipoVeicolo + ", nRuote=" + nRuote
				+ ", idAlimentazione=" + idAlimentazione + ", idCategoria=" + idCategoria + ", idColoe=" + idColoe
				+ ", idMarca=" + idMarca + ", annoProd=" + annoProd + ", modello=" + modello + "]";
	}
	
	
	
}
