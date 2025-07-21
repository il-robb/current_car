package com.betacom.car.models;

public class Uffici {

	private Integer id_uffici ;
	private String nome_ufficio;
	
	public Integer getId_uffici() {
		return id_uffici;
	}
	public void setId_uffici(Integer id_uffici) {
		this.id_uffici = id_uffici;
	}
	public String getNome_ufficio() {
		return nome_ufficio;
	}
	public void setNome_ufficio(String nome_ufficio) {
		this.nome_ufficio = nome_ufficio;
	}
	@Override
	public String toString() {
		return "Uffici [id_ufficio=" + id_uffici + ", nome_ufficio=" + nome_ufficio + "]";
	}
	
}

