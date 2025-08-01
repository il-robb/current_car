package com.betacom.jpa.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name="categoria")
public class Categoria {
	
	@Id
	@Column (
			nullable=false,
			name="id_categoria"
			)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCategoria;
	
	@Column (
			nullable=false
			)
	private String descrizione;

	@OneToMany(
			mappedBy = "categoria",
			fetch = FetchType.EAGER
			)
	@ToString.Exclude
	private List<Veicolo> veicolo;
	
	@ManyToMany(
			mappedBy = "categoria",
			fetch = FetchType.EAGER
			)
	@ToString.Exclude
	private List<TipoVeicolo> tipoVeicolo;

	
	
}
