package com.betacom.jpa.requests;

import java.time.LocalDate;

import com.betacom.jpa.models.Socio;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AbbonamentoReq {
	private Integer id;
	private LocalDate dataIscrizione;
	private Integer socioId;   
	
}
