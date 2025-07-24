package com.betacom.jpa.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class SocioDTO {

	private Integer id;
	private String cognome;
	private String nome;
	private String codiceFiscale;
	private String email;
	private CertificatoDTO certificato;

}
