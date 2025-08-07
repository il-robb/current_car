package com.betacom.jpa.services.interfaces;

import java.util.List;

import com.betacom.jpa.dto.SocioDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.requests.CertificatoReq;

public interface ICertificatoServices {
	
	int create(CertificatoReq req) throws AcademyException;
	void delete(CertificatoReq req) throws AcademyException;
	void update(CertificatoReq req) throws AcademyException;
	
	List<SocioDTO> listAll();
	
}

/*
 * È una classe contrassegnata con l’annotazione @Service.
 * Contiene le regole, controlli e operazioni che rappresentano il funzionamento dell’app.
 * È il livello intermedio tra il controller (che riceve la richiesta) e la repository (che parla col database).
 */
