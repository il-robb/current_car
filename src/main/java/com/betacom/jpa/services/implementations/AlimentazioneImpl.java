package com.betacom.jpa.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.betacom.jpa.dto.AlimentazioneDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.models.Alimentazione;
import com.betacom.jpa.repositories.IAlimentazioneRepository;
import com.betacom.jpa.repositories.ITipoVeicoloRepository;
import com.betacom.jpa.request.AlimentazioneReq;
import com.betacom.jpa.services.interfaces.IAlimentazioneService;

@Service
public class AlimentazioneImpl implements IAlimentazioneService{

	private IAlimentazioneRepository aliR;
	private ITipoVeicoloRepository tipoVeicoloR;
	
	public AlimentazioneImpl(IAlimentazioneRepository aliR) {
		super();
		this.aliR = aliR;
	}

	@Override
	public void create(AlimentazioneReq aliReq) throws AcademyException {
		Optional<Alimentazione> a = aliR.findByDescrizione(aliReq.getDescrizione());  
		if(a.isPresent())throw new AcademyException("questo tipo di alimentazione esiste gia :"+aliReq.getDescrizione());
//		
//		Optional<TipoVeicolo> tv= tipoVeicoloR.findByDescrizione(aliReq.getTipoveicolo());
//		if(tv.isEmpty()) throw new AcademyException("tipo di veicolo inesistente :"+alireq);		
		Alimentazione ali = new Alimentazione();
		if(aliReq.getDescrizione() == null) {
			throw new AcademyException("Descrizione obbligatoria");
		}
		
		ali.setDescrizione(aliReq.getDescrizione());
//		ali.setIdAlimentazione(aliReq.getIdAlimentazione());
		
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
