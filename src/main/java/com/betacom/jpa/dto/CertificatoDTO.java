package com.betacom.jpa.dto;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Builder
@Getter
@Setter
public class CertificatoDTO {

	private Integer id;
	private Boolean tipo; 
	private LocalDate dataCertificato;
	
}
