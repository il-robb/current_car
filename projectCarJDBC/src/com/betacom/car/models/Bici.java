package com.betacom.car.models;

public class Bici {
	
	private Integer idBici;
	private Integer IdVeicolo;
	private Integer nMarce;
	private Integer idSospensione;
	private Boolean pieghevole;
	
	public Bici() {
		super();
	}

	public Bici(Integer idBici, Integer idVeicolo, Integer nMarce, Integer idSospensione, Boolean pieghevole) {
		super();
		this.idBici = idBici;
		IdVeicolo = idVeicolo;
		this.nMarce = nMarce;
		this.idSospensione = idSospensione;
		this.pieghevole = pieghevole;
	}

	public Integer getIdBici() {
		return idBici;
	}

	public void setIdBici(Integer idBici) {
		this.idBici = idBici;
	}

	public Integer getIdVeicolo() {
		return IdVeicolo;
	}

	public void setIdVeicolo(Integer idVeicolo) {
		IdVeicolo = idVeicolo;
	}

	public Integer getnMarce() {
		return nMarce;
	}

	public void setnMarce(Integer nMarce) {
		this.nMarce = nMarce;
	}

	public Integer getIdSospensione() {
		return idSospensione;
	}

	public void setIdSospensione(Integer idSospensione) {
		this.idSospensione = idSospensione;
	}

	public Boolean getPieghevole() {
		return pieghevole;
	}

	public void setPieghevole(Boolean pieghevole) {
		this.pieghevole = pieghevole;
	}

	@Override
	public String toString() {
		return "Bici [idBici=" + idBici + ", IdVeicolo=" + IdVeicolo + ", nMarce=" + nMarce + ", idSospensione="
				+ idSospensione + ", pieghevole=" + pieghevole + "]";
	}
	
	
}
