package com.betacom.jpa.dto;


import com.betacom.jpa.models.Alimentazione;
import com.betacom.jpa.models.Categoria;
import com.betacom.jpa.models.Colore;
import com.betacom.jpa.models.Marca;
import com.betacom.jpa.models.TipoVeicolo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class VeicoloDTO {
	
	private Integer idVeicolo;
	
	private TipoVeicoloDTO tipoVeicolo;
	
	private Integer numeroRuote;
	
	private CategoriaDTO categoria;
	
	private AlimentazioneDTO alimentazione;

	private ColoreDTO colore;
	
	private MarcaDTO marca;
	
	private String annoProduzione;

	private String modello;
	
	private MacchinaDTO macchina;
	
	private MotoDTO moto;
	
	private BiciDTO bici;
	
}
