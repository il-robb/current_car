package com.betacom.jpa.services.interfaces;

import java.util.List;

import com.betacom.jpa.dto.VeicoloDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.request.VeicoloReq;

public interface IVeicoloServices {
	
	void create(VeicoloReq vReq) throws AcademyException;

	List<VeicoloDTO> listAll() throws AcademyException;
	
}
