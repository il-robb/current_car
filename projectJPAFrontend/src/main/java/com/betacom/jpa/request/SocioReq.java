package com.betacom.jpa.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SocioReq {
	private Integer id;
	private String cognome;
	private String nome;
	private String codiceFiscale;
	private String email;
	private String attivita;
	private String errrorMsg;
}
