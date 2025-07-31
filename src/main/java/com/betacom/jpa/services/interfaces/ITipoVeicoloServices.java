package com.betacom.jpa.services.interfaces;

import java.util.List;

import com.betacom.jpa.dto.TipoVeicoloDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.request.TipoVeicoloReq;

public interface ITipoVeicoloServices {

	List<TipoVeicoloDTO> listAll();
	
	void create(TipoVeicoloReq treq)throws AcademyException;
	
	void createTipoVeicoloAlimentazione(TipoVeicoloReq treq)throws AcademyException;
}
