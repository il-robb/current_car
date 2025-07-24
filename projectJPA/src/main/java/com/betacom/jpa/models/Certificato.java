package com.betacom.jpa.models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Entity
@Table (name="certificato_medico")
public class Certificato {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column (name="tipo_centificato")
	private Boolean tipo;  //false normale true agonistico

	@Column (name="data_certificato")
	private LocalDate dataCertificato;
	
	@OneToOne
	@JoinColumn(
			name="socio_id",
			referencedColumnName = "id"
			)
	private Socio socio;   // object result delle foreign key
	
}
