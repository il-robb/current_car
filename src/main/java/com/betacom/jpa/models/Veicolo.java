package com.betacom.jpa.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="veicolo")
public class Veicolo {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="id_veicolo")
	private Integer idVeicolo;
	
	@ManyToOne
	@JoinColumn (name="id_tipo_veicolo")
	private TipoVeicolo tipoVeicolo;
	
	@Column (name="numero_ruote",
			nullable = false)	
	private Integer numeroRuote;
	
	@ManyToOne
	@JoinColumn (name="id_categoria")
	private Categoria categoria;
	
	@ManyToOne
	@JoinColumn (name="id_alimentazione")
	private Alimentazione alimentazione;

	@ManyToOne
	@JoinColumn (name="id_colore")
	private Colore colore;
	
	@ManyToOne
	@JoinColumn (name="id_marca")
	private Marca marca;
	
	@Column (length=100,
			nullable = false,
			name="anno_prod")
	private String annoProduzione;

	@Column (length=100,
			nullable = false,
			name="modello")
	private String modello;
	
	@OneToOne(
			mappedBy = "veicolo",
			cascade = CascadeType.REMOVE
			)
	private Macchina macchina;
	
	@OneToOne(
			mappedBy = "veicolo",
			cascade = CascadeType.REMOVE
			)
	private Moto moto;
	
	@OneToOne(
			mappedBy = "veicolo",
			cascade = CascadeType.REMOVE
			)
	private Bici bici;
	
}
