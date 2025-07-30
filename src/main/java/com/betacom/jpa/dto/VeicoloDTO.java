package com.betacom.jpa.dto;

import com.betacom.jpa.models.Alimentazione;
import com.betacom.jpa.models.Categoria;
import com.betacom.jpa.models.Colore;
import com.betacom.jpa.models.Marca;
import com.betacom.jpa.models.TipoVeicolo;

public class VeicoloDTO {
	
	private Integer idVeicolo;
	
	private TipoVeicolo tipoVeicolo;
	
	private Integer numeroRuote;
	
	private Categoria categoria;
	
	private Alimentazione alimentazione;

	private Colore colore;
	
	private Marca marca;
	
	private String annoProduzione;

	private String modello;
	
	private MacchinaDTO macchina;
	
	private MotoDTO moto;
	
	private BiciDTO bici;
	
}
