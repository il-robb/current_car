package com.betacom.jpa.models;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Moto {
	    
	    @Id
		@GeneratedValue (strategy = GenerationType.IDENTITY)
		@Column(name="id_moto")
		private Integer idMoto;
		
		@Column (
				nullable=false,
				unique=true
				)
		private String targa;
		
		@Column (
				nullable = false)	
		private Integer cilindrata;
		
		@OneToOne
		@JoinColumn(
				name="id_veicolo",
				referencedColumnName = "id_veicolo"
				)
		private Veicolo veicolo;
		
}
