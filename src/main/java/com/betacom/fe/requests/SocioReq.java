package com.betacom.fe.requests;

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

public class SocioReq {
	private Integer id; 
	private String nome; 
	private String cognome; 
	private String codiceFiscale;
	private String email;
	private String attivita;
	private String errorMsg;
	

}
