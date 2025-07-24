package com.betacom.jpa.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table (name="socio")
public class Socio {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column (length=100,
			nullable = false)
	private String cognome;
	
	@Column (length=100,
			nullable = false)	
	private String nome;
	
	@Column (name = "codice_fiscale",
			 nullable = false,
			 length=16,
			 unique=true)
	private String codiceFiscale;
	
	private String email;

	@OneToOne(
			mappedBy = "socio",
			cascade = CascadeType.REMOVE
			)
	private Certificato certificato;
}
