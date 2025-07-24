package com.betacom.jpa.requests;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString

public class SocioReq {
	private Integer id;
	private String cognome;
	private String nome;
	private String codiceFiscale;
	private String email;

}
