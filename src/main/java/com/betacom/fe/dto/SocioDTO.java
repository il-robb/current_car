package com.betacom.fe.dto;


import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Builder
@Getter
@Setter
public class SocioDTO {
	
	private Integer id; 
	private String cognome; 
	private String nome; 
	private String codiceFiscale;
	private String email;
	private CertificatoDTO certificato; 
	private List<AbbonamentoDTO> abbonamento;
	
	
}

/* Una classe DTO (Data Transfer Object) serve per trasferire dati in modo strutturato tra diverse parti di un'applicazione,
 * specialmente tra livelli come controller, servizi e repository.
 *
 * Un DTO è una classe semplice che contiene solo campi, costruttori e metodi getter/setter.
 * Non contiene logica (niente metodi complessi): serve solo per trasportare dati.
 * È spesso usato quando si vuole evitare di esporre direttamente le entità JPA 
 * (quelle collegate al database) verso il mondo esterno.
 * 
 */