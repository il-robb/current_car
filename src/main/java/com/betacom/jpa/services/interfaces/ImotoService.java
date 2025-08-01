package com.betacom.jpa.services.interfaces;

import java.util.List;

import com.betacom.jpa.dto.VeicoloDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.request.MotoReq;
import com.betacom.jpa.request.VeicoloReq;

public interface ImotoService {

	void create(MotoReq mReq, VeicoloReq veiReq) throws AcademyException;

	List<VeicoloDTO> listAllMoto() throws AcademyException;
}
