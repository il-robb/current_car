package com.betacom.jpa.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="macchina")
public class Macchina {
	    
	    @Id
		@GeneratedValue (strategy = GenerationType.IDENTITY)
		@Column(name="id_macchina")
		private Integer idMacchina;
		
		@Column (name="numero_porte",
				nullable = false)	
		private Integer numeroPorte;
		
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
