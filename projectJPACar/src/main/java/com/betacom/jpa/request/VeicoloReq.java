package com.betacom.jpa.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class VeicoloReq {

	private Integer idVeicolo;	
	private String tipoVeicolo;	
	private Integer numeroRuote;	
	private String categoria;	
	private String alimentazione;
	private String colore;	
	private String marca;	
	private String annoProduzione;
	private String modello;	
	private Integer macchina;	
	private Integer moto;	
	private Integer bici;
}
