package com.betacom.jpa.services.interfaces;

import java.util.List;

import com.betacom.jpa.dto.AttivitaDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.requests.AttivitaReq;

public interface IAttivitaServices {

	void create(AttivitaReq req) throws AcademyException;
	void update(AttivitaReq req) throws AcademyException;
	void delete(AttivitaReq req) throws AcademyException;
	
	void createAttivitaAbbonamento(AttivitaReq req) throws AcademyException;
	void removeAttivitaAbbonamento(AttivitaReq req) throws AcademyException;
	List<AttivitaDTO> list();
	
}
