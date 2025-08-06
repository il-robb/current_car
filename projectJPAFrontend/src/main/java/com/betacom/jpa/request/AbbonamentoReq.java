package com.betacom.jpa.request;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class AbbonamentoReq {

	private Integer id;	
	private LocalDate dataIscrizione;
	private Integer socioId;
	
}
