package com.betacom.jpa.services.interfaces;

import java.util.List;

import com.betacom.jpa.dto.VeicoloDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.request.BiciReq;
import com.betacom.jpa.request.MacchinaReq;
import com.betacom.jpa.request.MotoReq;
import com.betacom.jpa.request.VeicoloReq;

public interface IVeicoloServices {

	List<VeicoloDTO> listAll()throws AcademyException;
	
	void create(VeicoloReq vreq) throws AcademyException;
	
	void addAlimentazione(String descrizione, String descrizioneVeicolo) throws AcademyException;
}
