package com.betacom.fe.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class SocioReq {
	private Integer id; 
	private String cognome; 
	private String nome; 
	private String codiceFiscale;
	private String email;
	private String attivita;
	private String errorMsg;

}
