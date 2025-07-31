package com.betacom.jpa.services.interfaces;

import java.util.List;

import com.betacom.jpa.dto.AlimentazioneDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.request.AlimentazioneReq;

public interface IAlimentazioneService {
	void create(AlimentazioneReq aliReq) throws AcademyException;

	List<AlimentazioneDTO> listAll() throws AcademyException;

}
