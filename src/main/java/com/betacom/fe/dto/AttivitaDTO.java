package com.betacom.fe.dto;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class AttivitaDTO {
	private Integer id;
	private String descrizione;
	private BigDecimal prezzo;
	private Integer abbonamentoId;
}