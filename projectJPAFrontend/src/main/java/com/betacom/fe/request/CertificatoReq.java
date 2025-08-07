package com.betacom.fe.request;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CertificatoReq {
	
	private Integer id;
	private Boolean tipo; 
	private LocalDate dataCertificato;
	private Integer socioId;
    private String errMessage;

}
