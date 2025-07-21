package com.betacom.car.models;

public class Bici {

    private int id_bici;
    private int id_veicolo;
    private int n_marce;
    private int id_sospenzione;
    private boolean pieghevole;
    
	public Bici(int id_bici, int id_veicolo, int n_marce, int id_sospenzione, boolean pieghevole) {
		super();
		this.id_bici = id_bici;
		this.id_veicolo = id_veicolo;
		this.n_marce = n_marce;
		this.id_sospenzione = id_sospenzione;
		this.pieghevole = pieghevole;
	}
	public int getId_bici() {
		return id_bici;
	}
	public void setId_bici(int id_bici) {
		this.id_bici = id_bici;
	}
	public int getId_veicolo() {
		return id_veicolo;
	}
	public void setId_veicolo(int id_veicolo) {
		this.id_veicolo = id_veicolo;
	}
	public int getN_marce() {
		return n_marce;
	}
	public void setN_marce(int n_marce) {
		this.n_marce = n_marce;
	}
	public int getId_sospenzione() {
		return id_sospenzione;
	}
	public void setId_sospenzione(int id_sospenzione) {
		this.id_sospenzione = id_sospenzione;
	}
	public boolean isPieghevole() {
		return pieghevole;
	}
	public void setPieghevole(boolean pieghevole) {
		this.pieghevole = pieghevole;
	}
	@Override
	public String toString() {
		return "Bici [id_bici=" + id_bici + ", id_veicolo=" + id_veicolo + ", n_marce=" + n_marce + ", id_sospenzione="
				+ id_sospenzione + ", pieghevole=" + pieghevole + "]";
	}

 
}
