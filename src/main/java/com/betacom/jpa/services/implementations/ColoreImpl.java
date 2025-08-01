package com.betacom.jpa.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.jpa.dto.ColoreDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.models.Colore;
import com.betacom.jpa.reporitories.IColoreRepository;
import com.betacom.jpa.request.ColoreReq;
import com.betacom.jpa.services.interfaces.IColoreService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class ColoreImpl implements IColoreService{

	private IColoreRepository colR;
	
	
	public ColoreImpl(IColoreRepository colR) {
		this.colR = colR;
	}

	@Override
	public List<ColoreDTO> listAll() throws AcademyException {
		List<Colore> lC = colR.findAll();
		
		
		return lC.stream()
				.map(c -> ColoreDTO.builder()
						.idColore(c.getIdColore())
						.descrizione(c.getDescrizione())
						.build()
						)
						.collect(Collectors.toList());
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void create(ColoreReq colReq) throws AcademyException {
		log.debug("Create Marca");
		Optional<Colore> c = colR.findByDescrizione(colReq.getDescrizione());
		if(c.isPresent()) {
			throw new AcademyException("Colore già presente");
		}
		
		Colore col = new Colore();
		if(colReq.getDescrizione() == null) {
			throw new AcademyException("Descrizione obbligatoria");
		}
		col.setDescrizione(colReq.getDescrizione());
		colR.save(col);
	}

}