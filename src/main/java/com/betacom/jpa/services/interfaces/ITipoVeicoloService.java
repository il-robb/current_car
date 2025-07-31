package com.betacom.jpa.services.interfaces;

import java.util.List;

import com.betacom.jpa.dto.TipoVeicoloDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.request.AlimentazioneReq;
import com.betacom.jpa.request.CategoriaReq;
import com.betacom.jpa.request.TipoVeicoloReq;

public interface ITipoVeicoloService {
	void createAlimentazioneTipoVeicolo(AlimentazioneReq aliReq) throws AcademyException;
	void create(TipoVeicoloReq tipoReq) throws AcademyException;
	void collegaCategoriaTipoVeicolo(CategoriaReq cateReq) throws AcademyException;
	
	List<TipoVeicoloDTO> listall() ;
}
