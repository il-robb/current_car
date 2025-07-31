package com.betacom.jpa.services.interfaces;

import java.util.List;

import com.betacom.jpa.dto.MarcaDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.request.MarcaReq;

public interface IMarcaServices {

	List<MarcaDTO> listAll() throws AcademyException; 
	
	void create(MarcaReq markreq) throws AcademyException;
	
}
