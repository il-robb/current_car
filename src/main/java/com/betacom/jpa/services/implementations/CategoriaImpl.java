package com.betacom.jpa.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.betacom.jpa.dto.CategoriaDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.models.Categoria;
import com.betacom.jpa.reporitories.ICategoriaRepository;
import com.betacom.jpa.request.CategoriaReq;
import com.betacom.jpa.services.interfaces.IcategoriaService;

@Service
public class CategoriaImpl implements IcategoriaService{

	private ICategoriaRepository cateR;
	
	
	
	

	public CategoriaImpl(ICategoriaRepository cateR) {
		super();
		this.cateR = cateR;
	}

	@Override
	public void create(CategoriaReq cateReq) throws AcademyException {
		Optional<Categoria> a = cateR.findByDescrizione(cateReq.getDescrizione());  
		if(a.isPresent())throw new AcademyException("questo tipo di alimentazione esiste gia :"+cateReq.getDescrizione());	
		Categoria ali = new Categoria();
		ali.setDescrizione(cateReq.getDescrizione());
		
		cateR.save(ali);
	}

	@Override
	public List<CategoriaDTO> listAll() throws AcademyException {
		List<Categoria> lA = cateR.findAll();
		return lA.stream().map(a->CategoriaDTO.builder()
				.descrizione(a.getDescrizione())
				.idCategoria(a.getIdCategoria())
				.build()
				).collect(Collectors.toList());
	}

}
