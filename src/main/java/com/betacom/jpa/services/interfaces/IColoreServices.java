package com.betacom.jpa.services.interfaces;

import java.util.List;

import com.betacom.jpa.dto.ColoreDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.request.ColoreReq;

public interface IColoreServices {

	List<ColoreDTO> listAll() throws AcademyException; 
	
	void create(ColoreReq colReq) throws AcademyException;
}
