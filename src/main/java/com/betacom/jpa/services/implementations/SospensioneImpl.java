package com.betacom.jpa.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.jpa.dto.SospensioneDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.models.Sospensione;
import com.betacom.jpa.reporitories.ISospensioneRepository;
import com.betacom.jpa.request.SospensioneReq;
import com.betacom.jpa.services.interfaces.ISospensioneService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class SospensioneImpl implements ISospensioneService{

	private ISospensioneRepository sR;
	
	
	public SospensioneImpl(ISospensioneRepository sR) {
		this.sR = sR;
	}

	@Override
	public List<SospensioneDTO> listAll() throws AcademyException {
		List<Sospensione> lS = sR.findAll();
		
		return lS.stream()
				.map(s -> SospensioneDTO.builder()
						.idSospensione(s.getIdSospensione())
						.descrizione(s.getDescrizione())
						.build()
						)
				.collect(Collectors.toList());
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void create(SospensioneReq sreq) throws AcademyException {
		log.debug("Create Sospensione");
		Optional<Sospensione> s = sR.findByDescrizione(sreq.getDescrizione());
		if(s.isPresent()) {
			throw new AcademyException("Tipo Veicolo già presente");
		}
		
		Sospensione sp = new Sospensione();
		if(sreq.getDescrizione() == null) {
			throw new AcademyException("Descrizione obbligatoria");
		}
		sp.setDescrizione(sreq.getDescrizione());
		sR.save(sp);
	}

}