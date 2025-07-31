package com.betacom.jpa.services.interfaces;

import java.util.List;

import com.betacom.jpa.dto.CategoriaDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.request.CategoriaReq;

public interface IcategoriaService {
	void create(CategoriaReq cateReq) throws AcademyException;

	List<CategoriaDTO> listAll() throws AcademyException;

}
