package com.betacom.fe.requests;

import java.time.LocalDate;

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
public class CertificatoReq {
	
	private Integer id;
	private String tipo; 
	private LocalDate dataCertificato;
	private Integer socioId;

}
