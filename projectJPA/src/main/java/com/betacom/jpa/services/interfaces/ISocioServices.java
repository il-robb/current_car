package com.betacom.jpa.services.interfaces;

import java.util.List;

import com.betacom.jpa.dto.SocioDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.requests.SocioReq;

public interface ISocioServices {

	Integer insert(SocioReq req) throws AcademyException;
	
	List<SocioDTO> listAll();
}
