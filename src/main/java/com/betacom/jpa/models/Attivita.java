package com.betacom.jpa.models;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table (name="attivita")
public class Attivita {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(
			length = 100,
			name="descrizione_attivita",
			nullable = false)
	private String descrizione;
	
	private BigDecimal prezzo;
	
	@ManyToMany(
			mappedBy = "attivita",
			fetch = FetchType.EAGER
			)
	private List<Abbonamento> abbonamento;
}
