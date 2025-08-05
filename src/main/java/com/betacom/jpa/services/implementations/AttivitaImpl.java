package com.betacom.jpa.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.jpa.dto.AttivitaDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.models.Abbonamento;
import com.betacom.jpa.models.Attivita;
import com.betacom.jpa.repositories.IAbbonamentoRepository;
import com.betacom.jpa.repositories.IAttivitaRepository;
import com.betacom.jpa.requests.AttivitaReq;
import com.betacom.jpa.services.interfaces.IAttivitaServices;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class AttivitaImpl implements IAttivitaServices{

	private IAttivitaRepository attivR;
	private IAbbonamentoRepository abboR;

	public AttivitaImpl(IAttivitaRepository attivR, IAbbonamentoRepository abboR) {
		this.attivR = attivR;
		this.abboR = abboR;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void create(AttivitaReq req) throws AcademyException {
		log.debug("create :" + req);
		if (req.getDescrizione() == null)
			throw new AcademyException("Descrizione non presente");
		Optional<Attivita> a = attivR.findByDescrizione(req.getDescrizione().trim().toUpperCase());
		if (a.isPresent())
			throw new AcademyException("Attività già presente in database :" + req.getDescrizione());
		Attivita at = new Attivita();
		at.setDescrizione(req.getDescrizione().trim().toUpperCase());
		if (req.getPrezzo() == null)
			throw new AcademyException("Prezzo non presente");
		at.setPrezzo(req.getPrezzo());
		
		attivR.save(at);
	}
	
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void update(AttivitaReq req) throws AcademyException {
		log.debug("update :" + req);
		Optional<Attivita> at = attivR.findById(req.getId());
		if (at.isEmpty())
			throw new AcademyException("Attivia non presente in database :" + req.getId());
		if (req.getDescrizione() != null) {
			Optional<Attivita> a = attivR.findByDescrizione(req.getDescrizione().trim().toUpperCase());
			if (a.isPresent())
				throw new AcademyException("Attività già presente in database :" + req.getDescrizione());
			at.get().setDescrizione(req.getDescrizione().trim().toUpperCase());			
		}
		if (req.getPrezzo() != null)
			at.get().setPrezzo(req.getPrezzo());
		
		attivR.save(at.get());
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void delete(AttivitaReq req) throws AcademyException {
		log.debug("delete :" + req);

		Optional<Attivita> at = attivR.findById(req.getId());
		if (at.isEmpty())
			throw new AcademyException("Attivia non presente in database :" + req.getId());

		if (!at.get().getAbbonamento().isEmpty())
			throw new AcademyException("Ci sono abbonamenti collegati a questa attività :" + req.getId());

		attivR.delete(at.get());
	}
	
	@Override
	public List<AttivitaDTO> list() {
		log.debug("List");
		List<Attivita> lA = attivR.findAll();
		return lA.stream()
				.map(a -> AttivitaDTO.builder()
						.id(a.getId())
						.descrizione(a.getDescrizione())
						.prezzo(a.getPrezzo())
						.build()
						)
				.collect(Collectors.toList());
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void createAttivitaAbbonamento(AttivitaReq req) throws AcademyException {
		log.debug("createAttivitaAbbonamento :" + req);
		Optional<Abbonamento> abb = abboR.findById(req.getAbbonamentiId());
		if (abb.isEmpty())
			throw new AcademyException("Abbonamento non presente in database :" + req.getAbbonamentiId());
		
		Optional<Attivita> att = attivR.findById(req.getId());
		if (att.isEmpty())
			throw new AcademyException("Attivita non presente in database :" + req.getId());
		
		abb.get().getAttivita().add(att.get());
		abboR.save(abb.get());
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void removeAttivitaAbbonamento(AttivitaReq req) throws AcademyException {
		Optional<Abbonamento> abb = abboR.findById(req.getAbbonamentiId());  // control abbonamento
		if (abb.isEmpty())
			throw new AcademyException("Abbonamento non trovato");
		log.debug("Attivita size:" + abb.get().getAttivita().size());
		abb.ifPresent(abObj -> 
			abObj.getAttivita().stream()
				.filter(a -> a.getId() == req.getId())
				.findFirst()
				.ifPresent(abObj.getAttivita()::remove)    //L’espressione getAttivita()::remove è una method reference
				);                              // abObj.getAttivita().remove(a))
												// è una scorciatoia per dire:
		                                        //quando ho un oggetto Attivita, passalo al metodo remove() di quella lista
		log.debug("After Attivita size:" + abb.get().getAttivita().size());
		
		abboR.save(abb.get());
		
	}


}
