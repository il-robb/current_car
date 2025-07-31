package com.betacom.jpa.utils;

import java.math.BigDecimal;
import java.text.Bidi;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.betacom.jpa.dto.BiciDTO;
import com.betacom.jpa.dto.MacchinaDTO;
import com.betacom.jpa.dto.MotoDTO;
import com.betacom.jpa.dto.SospensioneDTO;
import com.betacom.jpa.models.Bici;
import com.betacom.jpa.models.Macchina;
import com.betacom.jpa.models.Moto;
import com.betacom.jpa.models.Sospensione;


public class Utilities {
	
	
	public MacchinaDTO buildMacchina(Macchina car){
		return MacchinaDTO.builder()
				.idMacchina(car.getIdMacchina())
				.numeroPorte(car.getNumeroPorte())
				.targa(car.getTarga())
				.cilindrata(car.getCilindrata())
				.build();
	}
	
	public MotoDTO builMoto(Moto moto) {
		return MotoDTO.builder()
				.idMoto(moto.getIdMoto())
				.targa(moto.getTarga())
				.cilindrata(moto.getCilindrata())
				.build();
	}
	
	public BiciDTO buildBici(Bici bici) {
		return BiciDTO.builder()
				.idBici(bici.getIdBici())
				.numeroMarce(bici.getNumeroMarce())
				.pieghevole(bici.getPieghevole())
				.sospensione(bici.getSospensione().getDescrizione())
				.build();
	}
	
	public SospensioneDTO buildSospensione(Sospensione sos) {
		return SospensioneDTO.builder()
				.idSospensione(sos.getIdSospensione())
				.descrizione(sos.getDescrizione())
				.build();
	}
}
