package com.betacom.jpa.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MacchinaReq {

	private Integer idMacchina;
	private Integer numeroPorte;
	private String targa;
	private Integer cilindrata;
}
