package com.betacom.jpa.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TipoVeicoloDTO {
	
	private Integer idTipoVeicolo;
	private String descrizione;
	private String pattern;
}
