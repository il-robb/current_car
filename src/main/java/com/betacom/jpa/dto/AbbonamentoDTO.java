package com.betacom.jpa.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder
public class AbbonamentoDTO {
	private Integer id;
	private LocalDate dataIscrizione;
	private List<AttivitaDTO> attivita;
}
