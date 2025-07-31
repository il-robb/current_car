package com.betacom.jpa.services.interfaces;

import java.util.List;

import com.betacom.jpa.dto.VeicoloDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.request.BiciReq;

public interface IBiciService {
	void create(BiciReq biciReq) throws AcademyException;

	List<VeicoloDTO> listAllBici() throws AcademyException;
}
