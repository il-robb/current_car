package com.betacom.jpa.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.jpa.dto.CategoriaDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.models.Categoria;
import com.betacom.jpa.repositories.ICategoriaRepository;
import com.betacom.jpa.request.CategoriaReq;
import com.betacom.jpa.services.interfaces.ICategoriaServices;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class CategoriaImpl implements ICategoriaServices{

	private ICategoriaRepository catR;
	
	
	
	public CategoriaImpl(ICategoriaRepository catR) {
		this.catR = catR;
	}

	@Override
	public List<CategoriaDTO> listAll() {
		List<Categoria> lC = catR.findAll();
		
		return lC.stream()
				.map(c -> CategoriaDTO.builder()
						.idCategoria(c.getIdCategoria())
						.descrizione(c.getDescrizione())
						.build()
						)
				.collect(Collectors.toList());
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void create(CategoriaReq creq) throws AcademyException {
		log.debug("Create Categoria");
		Optional<Categoria> c = catR.findByDescrizione(creq.getDescrizione());
		if(c.isPresent()) {
			throw new AcademyException("Categoria già presente");
		}
		
		Categoria cat = new Categoria();
		cat.setIdCategoria(creq.getIdCategoria());
		cat.setDescrizione(creq.getDescrizione());
		
		catR.save(cat);
	}

}
