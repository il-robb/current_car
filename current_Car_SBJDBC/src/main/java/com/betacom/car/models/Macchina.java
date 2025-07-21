package com.betacom.car.models;

public class Macchina{

    private int id_macchina;
    private int id_veicolo;
    private int n_porte;
    private String targa;
    private int cilindrata;

    public Macchina() {
        super();
    }

	public Macchina(int id_macchina, int id_veicolo, int n_porte, String targa, int cilindrata) {
		super();
		this.id_macchina = id_macchina;
		this.id_veicolo = id_veicolo;
		this.n_porte = n_porte;
		this.targa = targa;
		this.cilindrata = cilindrata;
	}

	public int getId_macchina() {
		return id_macchina;
	}

	public void setId_macchina(int id_macchina) {
		this.id_macchina = id_macchina;
	}

	public int getId_veicolo() {
		return id_veicolo;
	}

	public void setId_veicolo(int id_veicolo) {
		this.id_veicolo = id_veicolo;
	}

	public int getN_porte() {
		return n_porte;
	}

	public void setN_porte(int n_porte) {
		this.n_porte = n_porte;
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
		return "Macchina [id_macchina=" + id_macchina + ", id_veicolo=" + id_veicolo + ", n_porte=" + n_porte
				+ ", targa=" + targa + ", cilindrata=" + cilindrata + "]";
	}

    
}