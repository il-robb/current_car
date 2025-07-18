package com.betacom.car.models;

public class Moto {

	private int id_moto;
	private int id_veicolo;
	private String targa;
	private int cilindrata;
	
	
	
	public Moto() {
		super();
	}

	public Moto(int id_moto, int id_veicolo, String targa, int cilindrata) {
		super();
		this.id_moto = id_moto;
		this.id_veicolo = id_veicolo;
		this.targa = targa;
		this.cilindrata = cilindrata;
	}
	
	public int getId_moto() {
		return id_moto;
	}
	public void setId_moto(int id_moto) {
		this.id_moto = id_moto;
	}
	public int getId_veicolo() {
		return id_veicolo;
	}
	public void setId_veicolo(int id_veicolo) {
		this.id_veicolo = id_veicolo;
	}
	public String getTarga() {
		return targa;
	}
	public void setTarga(String targa) {
		this.targa = targa;
	}
	public int getCilindrata() {
		return cilindrata;
	}
	public void setCilindrata(int cilindrata) {
		this.cilindrata = cilindrata;
	}
	
	@Override
	public String toString() {
		return "id_moto=" + id_moto + ", id_veicolo=" + id_veicolo + ", targa=" + targa + ", cilindrata="
				+ cilindrata + "]";
	}
	
	
}
