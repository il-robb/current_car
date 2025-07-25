package com.betacom.jpa.requests;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttivitaReq {
	private Integer attivitaId;
	private String descrizione;
	private BigDecimal prezzo;
	private Integer abbonamentoId;   
	
}
