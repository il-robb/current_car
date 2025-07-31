package com.betacom.jpa.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.betacom.jpa.dto.AlimentazioneDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.models.Alimentazione;
import com.betacom.jpa.reporitories.IAlimentazioneRepository;
import com.betacom.jpa.request.AlimentazioneReq;
import com.betacom.jpa.services.interfaces.IAlimentazioneService;

@Service
public class AlimentazioneImpl implements IAlimentazioneService{

	private IAlimentazioneRepository aliR;
	
	public AlimentazioneImpl(IAlimentazioneRepository aliR) {
		super();
		this.aliR = aliR;
	}

	@Override
	public void create(AlimentazioneReq aliReq) throws AcademyException {
		Optional<Alimentazione> a = aliR.findByDescrizione(aliReq.getDescrizione());  
		if(a.isPresent())throw new AcademyException("questo tipo di alimentazione esiste gia :"+aliReq.getDescrizione());	
		Alimentazione ali = new Alimentazione();
		ali.setDescrizione(aliReq.getDescrizione());
		
		aliR.save(ali);
	}

	@Override
	public List<AlimentazioneDTO> listAll() throws AcademyException {
		List<Alimentazione> lA = aliR.findAll();
		return lA.stream().map(a->AlimentazioneDTO.builder()
				.descrizione(a.getDescrizione())
				.idAlimentazione(a.getIdAlimentazione())
				.build()
				).collect(Collectors.toList());
	}

}
