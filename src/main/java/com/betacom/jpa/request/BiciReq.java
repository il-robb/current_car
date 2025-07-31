package com.betacom.jpa.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BiciReq {

	private Integer idBici;	
	private Integer numeroMarce;	
	private Boolean pieghevole;	
	private String sospensione;
}
