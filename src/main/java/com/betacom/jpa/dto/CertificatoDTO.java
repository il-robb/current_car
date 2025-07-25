package com.betacom.jpa.dto;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder
public class CertificatoDTO {

	private Integer id;
	private Boolean tipo;  //false normale true agonistico
	private LocalDate dataCertificato;

}
