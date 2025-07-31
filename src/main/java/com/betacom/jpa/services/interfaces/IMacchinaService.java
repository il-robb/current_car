package com.betacom.jpa.services.interfaces;

import java.util.List;

import com.betacom.jpa.dto.VeicoloDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.request.MacchinaReq;

public interface IMacchinaService {
	void create(MacchinaReq CarReq) throws AcademyException;
	
	List<VeicoloDTO> listAllCar() throws AcademyException;
	
}
