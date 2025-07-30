package com.betacom.jpa.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="categoria")
public class Categoria {
	
	@Id
	@Column (
			nullable=false,
			name="id_categoria"
			)
	private Integer idCategoria;
	
	@Column (
			nullable=false
			)
	private String descrizione;

	@OneToMany(
			mappedBy = "categoria",
			fetch = FetchType.EAGER
			)
	private List<Veicolo> veicolo;
}
