package com.betacom.jpa.services.interfaces;

import java.util.List;

import com.betacom.jpa.dto.CertificatoDTO;
import com.betacom.jpa.dto.SocioDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.requests.CertificatoReq;

public interface ICertificatoService {
	int create(CertificatoReq req) throws AcademyException;
	void delete(CertificatoReq req) throws AcademyException;
	
	List<SocioDTO> listAll() ;

}
