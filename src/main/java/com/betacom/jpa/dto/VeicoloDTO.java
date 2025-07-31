package com.betacom.jpa.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class VeicoloDTO {
	
	private Integer idVeicolo;
	
	private String tipoVeicolo;
	
	private Integer numeroRuote;
	
	private String categoria;
	
	private String alimentazione;

	private String colore;
	
	private String marca;
	
	private String annoProduzione;

	private String modello;
	
//	private ResponseObject<?> specifico = tipospecifico();
	
	private MacchinaDTO macchina;
	
	private MotoDTO moto;
	
	private BiciDTO bici;
	
	
//	private ResponseObject<?> tipospecifico()
//	{
//		return new MacchinaDTO();
//	}
}
