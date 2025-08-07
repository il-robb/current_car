package com.betacom.fe.requests;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class AttivitaReq {
	private Integer id;
	private String descrizione;
	private BigDecimal prezzo;
	private Integer abbonamentoId;
}
