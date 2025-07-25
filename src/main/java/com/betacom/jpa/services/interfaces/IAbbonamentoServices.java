package com.betacom.jpa.services.interfaces;

import java.util.List;

import com.betacom.jpa.dto.AbbonamentoDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.requests.AbbonamentoReq;

public interface IAbbonamentoServices {
	
	AbbonamentoDTO getById(Integer id) throws AcademyException;

	void create(AbbonamentoReq req) throws AcademyException;
	void update(AbbonamentoReq req) throws AcademyException;
	
	List<AbbonamentoDTO> listAll();
}
