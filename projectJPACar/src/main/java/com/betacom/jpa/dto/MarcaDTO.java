package com.betacom.jpa.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MarcaDTO {

	private Integer idMarca;
	private String descrizione;
}
