package com.betacom.jpa.request;

import lombok.Data;

@Data
public class MacchinaVeicoloReq {
	VeicoloReq datiVeicolo;
	MacchinaReq datiMacchina;
}
