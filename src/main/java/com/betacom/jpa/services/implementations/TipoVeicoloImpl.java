package com.betacom.jpa.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.jpa.dto.TipoVeicoloDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.models.Alimentazione;
import com.betacom.jpa.models.TipoVeicolo;
import com.betacom.jpa.repositories.IAlimentazioneRepository;
import com.betacom.jpa.repositories.ITipoVeicoloRepository;
import com.betacom.jpa.request.TipoVeicoloReq;
import com.betacom.jpa.services.interfaces.ITipoVeicoloServices;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class TipoVeicoloImpl implements ITipoVeicoloServices{
	
	private ITipoVeicoloRepository tvR;
	private IAlimentazioneRepository alimR;
	
	

	public TipoVeicoloImpl(ITipoVeicoloRepository tvR, IAlimentazioneRepository alimR) {
		this.tvR = tvR;
		this.alimR = alimR;
	}

	@Override
	public List<TipoVeicoloDTO> listAll() {
		List<TipoVeicolo> lTv = tvR.findAll();
		
		return lTv.stream()
				.map(tv -> TipoVeicoloDTO.builder()
						.idTipoVeicolo(tv.getIdTipoVeicolo())
						.descrizione(tv.getDescrizione())
						.pattern(tv.getPattern())
						.build())
				.collect(Collectors.toList());
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void create(TipoVeicoloReq treq) throws AcademyException {
		log.debug("Create Tipo Veicolo");
		Optional<TipoVeicolo> t = tvR.findByDescrizione(treq.getDescrizione());
		if(t.isPresent()) {
			throw new AcademyException("Tipo Veicolo già presente");
		}
		
		TipoVeicolo tv = new TipoVeicolo();
		if(treq.getDescrizione() == null) {
			throw new AcademyException("Descrizione obbligatoria");
		}
		if(treq.getPattern() ==null) {
			throw new AcademyException("Pattern obbligatoria");
		}
		
		tv.setDescrizione(treq.getDescrizione());
		tv.setPattern(treq.getPattern());
		
		tvR.save(tv);
		
	}

	@Transactional (rollbackFor = Exception.class)
	@Override
	public void createTipoVeicoloAlimentazione(TipoVeicoloReq treq) throws AcademyException{
		log.debug("create: " + treq);
	 Optional<Alimentazione> alim = alimR.findById(treq.getIdAlimentazine());
	 if(alim.isEmpty()) {
		 throw new AcademyException("Alimentazione inesistente database");
	 }
	 Optional<TipoVeicolo> tv = tvR.findById(treq.getIdAlimentazine());
	 if(tv.isEmpty()) {
		 throw new AcademyException("TipoVeicolo inesistente database");
	 }
	 
	 alim.get().getTipoVeicolo().add(tv.get());
	 alimR.save(alim.get());
	}

}
