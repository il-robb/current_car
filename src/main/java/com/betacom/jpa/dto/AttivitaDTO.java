package com.betacom.jpa.dto;


import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AttivitaDTO {
	
	private Integer idAttivita;
	private String descrizione;
	private BigDecimal prezzo;   
	
}

