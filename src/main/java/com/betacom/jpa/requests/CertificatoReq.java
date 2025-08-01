package com.betacom.jpa.requests;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CertificatoReq {
	
	private Integer id;
	private Boolean tipo; 
	private LocalDate dataCertificato;
	private Integer socioId;

}
