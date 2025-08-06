package com.betacom.jpa.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ContoDTO {
	String nome;
	String cognome;
	List<AttivitaDTO> attivita;
	BigDecimal Totale;
}
