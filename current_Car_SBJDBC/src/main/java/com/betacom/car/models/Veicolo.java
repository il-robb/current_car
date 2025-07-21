package com.betacom.car.models;

public class Veicolo {
 
	private int id_veicolo;
	private String tipo_veicolo; //macchina, moto, bici
	private int numero_ruote;
	private String id_alimentazione;
	private String id_categoria;
	private String id_colore;
	private String id_marca;
	private String anno_prod;
	private String modello;
	
	public Veicolo() {}
	
	public Veicolo(int id_veicolo, String tipo_veicolo, int numero_ruote, String id_alimentazione, String id_categoria,
			String id_colore, String id_marca, String anno_prod, String modello) {
		super();
		this.id_veicolo = id_veicolo;
		this.tipo_veicolo = tipo_veicolo;
		this.numero_ruote = numero_ruote;
		this.id_alimentazione = id_alimentazione;
		this.id_categoria = id_categoria;
		this.id_colore = id_colore;
		this.id_marca = id_marca;
		this.anno_prod = anno_prod;
		this.modello = modello;
	}
	public int getId_veicolo() {
		return id_veicolo;
	}
	public void setId_veicolo(int id_veicolo) {
		this.id_veicolo = id_veicolo;
	}
	public String getTipo_veicolo() {
		return tipo_veicolo;
	}
	public void setTipo_veicolo(String tipo_veicolo) {
		this.tipo_veicolo = tipo_veicolo;
	}
	public int getNumero_ruote() {
		return numero_ruote;
	}
	public void setNumero_ruote(int numero_ruote) {
		this.numero_ruote = numero_ruote;
	}
	public String getId_alimentazione() {
		return id_alimentazione;
	}
	public void setId_alimentazione(String id_alimentazione) {
		this.id_alimentazione = id_alimentazione;
	}
	public String getId_categoria() {
		return id_categoria;
	}
	public void setId_categoria(String id_categoria) {
		this.id_categoria = id_categoria;
	}
	public String getId_colore() {
		return id_colore;
	}
	public void setId_colore(String id_colore) {
		this.id_colore = id_colore;
	}
	public String getId_marca() {
		return id_marca;
	}
	public void setId_marca(String id_marca) {
		this.id_marca = id_marca;
	}
	public String getAnno_prod() {
		return anno_prod;
	}
	public void setAnno_prod(String anno_prod) {
		this.anno_prod = anno_prod;
	}
	public String getModello() {
		return modello;
	}
	public void setModello(String modello) {
		this.modello = modello;
	}
	@Override
	public String toString() {
		return "Veicolo [id_veicolo=" + id_veicolo + ", tipo_veicolo=" + tipo_veicolo + ", numero_ruote=" + numero_ruote
				+ ", id_alimentazione=" + id_alimentazione + ", id_categoria=" + id_categoria + ", id_colore="
				+ id_colore + ", id_marca=" + id_marca + ", anno_prod=" + anno_prod + ", modello=" + modello + "]";
	}
	
	
	
	
	
	
	
	
	
	
}