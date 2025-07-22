package com.betacom.jpa.dto;

import com.betacom.jpa.models.Socio;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SocioDTO {

	private Integer id;
	private String cognome;
	private String nome;
	private String codiceFiscale;
	private String email;
	private CertificatoDTO certificatoDTO;

}
