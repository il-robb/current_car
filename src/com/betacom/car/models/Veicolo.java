package com.betacom.car.models;

public class Veicolo {
 
	private int idVeicolo;
	private String tipoVeicolo; //macchina, moto, bici
	private int numeroRuote;
	private int idAlimentazione;
	private int idCategoria;
	private int idColore;
	private int idMarca;
	private int annoProduzione;
	private String modello;
	
	public Veicolo(int idVeicolo, String tipoVeicolo, int numeroRuote, int idAlimentazione, int idCategoria,
			int idColore, int idMarca, int annoProduzione, String modello) {
		super();
		this.idVeicolo = idVeicolo;
		this.tipoVeicolo = tipoVeicolo;
		this.numeroRuote = numeroRuote;
		this.idAlimentazione = idAlimentazione;
		this.idCategoria = idCategoria;
		this.idColore = idColore;
		this.idMarca = idMarca;
		this.annoProduzione = annoProduzione;
		this.modello = modello;
	}

	public int getIdVeicolo() {
		return idVeicolo;
	}
	public void setIdVeicolo(int idVeicolo) {
		this.idVeicolo = idVeicolo;
	}

	public String getTipoVeicolo() {
		return tipoVeicolo;
	}
	public void setTipoVeicolo(String tipoVeicolo) {
		this.tipoVeicolo = tipoVeicolo;
	}

	public int getNumeroRuote() {
		return numeroRuote;
	}
	public void setNumeroRuote(int numeroRuote) {
		this.numeroRuote = numeroRuote;
	}

	public int getIdAlimentazione() {
		return idAlimentazione;
	}
	public void setIdAlimentazione(int idAlimentazione) {
		this.idAlimentazione = idAlimentazione;
	}

	public int getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public int getIdColore() {
		return idColore;
	}
	public void setIdColore(int idColore) {
		this.idColore = idColore;
	}

	public int getIdMarca() {
		return idMarca;
	}
	public void setIdMarca(int idMarca) {
		this.idMarca = idMarca;
	}

	public int getAnnoProduzione() {
		return annoProduzione;
	}
	public void setAnnoProduzione(int annoProduzione) {
		this.annoProduzione = annoProduzione;
	}

	public String getModello() {
		return modello;
	}
	public void setModello(String modello) {
		this.modello = modello;
	}
	
	@Override
	public String toString() {
		return "Veicolo [idVeicolo=" + idVeicolo + ", tipoVeicolo=" + tipoVeicolo + ", numeroRuote=" + numeroRuote
				+ ", idAlimentazione=" + idAlimentazione + ", idCategoria=" + idCategoria + ", idColore=" + idColore
				+ ", idMarca=" + idMarca + ", annoProduzione=" + annoProduzione + ", modello=" + modello + "]";
	}
	
	
}