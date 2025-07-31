package com.betacom.jpa.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TipoVeicoloReq {

	private Integer idTipoVeicolo;
	private String descrizione;
	private String pattern;
	private Integer idAlimentazine;
}
