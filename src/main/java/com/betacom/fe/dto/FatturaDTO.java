package com.betacom.fe.dto;
import java.math.BigDecimal;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class FatturaDTO {
	private String nome;
	private String cognome;
	private List<AttivitaDTO> attivita;
	private BigDecimal totalePrezzoAttivita;
	List<AttivitaDTO> riga;

}
