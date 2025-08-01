package com.betacom.jpa.services.interfaces;

import java.util.List;

import com.betacom.jpa.dto.SospensioneDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.request.SospensioneReq;

public interface ISospensioneService {
	
	List<SospensioneDTO> listAll() throws AcademyException; 
	
	void create(SospensioneReq sreq) throws AcademyException;
}