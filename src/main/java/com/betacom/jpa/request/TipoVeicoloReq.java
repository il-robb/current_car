package com.betacom.jpa.request;

import lombok.Data;

@Data
public class TipoVeicoloReq {

	private Integer idTipoVeicolo;
	
	private String descrizione;
	
	private String pattern;
	
	private String tipoAlimentazione;
	
}
