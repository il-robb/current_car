package com.betacom.jpa.services.interfaces;

import java.util.List;

import com.betacom.jpa.dto.SocioDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.requests.SocioReq;

public interface ISocioServices {

	Integer insert(SocioReq req) throws AcademyException;
	void delete(SocioReq req) throws AcademyException;
	
	void update(SocioReq req) throws AcademyException;
	
	SocioDTO getSocio(Integer id) throws AcademyException;
	
	List<SocioDTO> listAll();

	List<SocioDTO> listByAttivita(String attivita);
	
	List<SocioDTO> list(Integer id, String nome, String cognome, String attivita);

}
