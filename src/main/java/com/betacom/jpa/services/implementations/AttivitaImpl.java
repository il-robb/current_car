package com.betacom.jpa.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; //occhio all'import per transaction

import com.betacom.jpa.dto.AttivitaDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.models.Abbonamento;
import com.betacom.jpa.models.Attivita;
import com.betacom.jpa.repositories.IAbbonamentiRepository;
import com.betacom.jpa.repositories.IAttivitaRepository;
import com.betacom.jpa.requests.AttivitaReq;
import com.betacom.jpa.services.interfaces.IAttivitaServices;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class AttivitaImpl implements IAttivitaServices{
	
	private IAttivitaRepository attiR;
	private IAbbonamentiRepository abboR;
	
	public AttivitaImpl(IAttivitaRepository attiR, IAbbonamentiRepository abboR) {
		super();
		this.attiR = attiR;
		this.abboR = abboR;
	}

	@Override
	public void create(AttivitaReq req) throws AcademyException {
		log.debug("Create: " +req);
		Optional<Attivita> oa = attiR.findByDescrizione(req.getDescrizione().trim().toUpperCase());
		if(oa.isPresent()) {
			throw new AcademyException("descrizione già pesente nel db");
		}
		Attivita att = new Attivita();
		if (req.getPrezzo() == null) {
			throw new AcademyException("prezzo OBBLIGATORIO");
		}
		if (req.getDescrizione()== null) {
			throw new AcademyException("descrizione OBBLIGATORIA");
		}
		att.setPrezzo(req.getPrezzo());
		att.setDescrizione(req.getDescrizione());
		attiR.save(att);
	}
		
	@Override
	public void update(AttivitaReq req) throws AcademyException {
		log.debug("update: " + req);
		Optional<Attivita> a = attiR.findById(req.getId());
		if (a.isEmpty()) {
			throw new AcademyException("Attività non trovata nel db: " +req.getId());
		}
		Attivita att = a.get();
		a = attiR.findByDescrizione(req.getDescrizione().trim().toUpperCase());
		if (req.getDescrizione() != null) {
			if(a.isPresent()) {
				throw new AcademyException("descrizione già pesente nel db");
			} else {
				att.setDescrizione(req.getDescrizione());
			}
		}
		if (req.getPrezzo() != null) {
			att.setPrezzo(req.getPrezzo());
		}
		attiR.save(att); 
	}
		
	@Override
	public void delete(AttivitaReq req) throws AcademyException {
		log.debug("Delete: " + req);
		Optional<Attivita> a = attiR.findById(req.getId());
		if (a.isEmpty()) {
			throw new AcademyException("Attività non trovata nel db: " +req.getId());
		}
		attiR.delete(a.get());
	}

	@Override
	public List<AttivitaDTO> list() {
		List<Attivita> lA = attiR.findAll();
		return lA.stream()
				.map(a -> AttivitaDTO.builder()
						.id(a.getId())
						.descrizione(a.getDescrizione())
						.build()).collect(Collectors.toList());
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void createAttivitaAbbonamento(AttivitaReq req) throws AcademyException {
		log.debug("createAttivitaAbbonamento: " + req);
		Optional<Abbonamento> ab = abboR.findById(req.getAbbonamentoId());
		if (ab.isEmpty()) {
			throw new AcademyException("Abbonamento non trovato nel db: " +req.getAbbonamentoId());
		}
		Optional<Attivita> at = attiR.findById(req.getId());
		if (at.isEmpty()) {
			throw new AcademyException("Attività non trovata nel db: " +req.getId());
		}
		ab.get().getAttivita().add(at.get());
		abboR.save(ab.get());
	}
	/*
	@Override
	public void removeAttivitaAbbonamento(AttivitaReq req) throws AcademyException {
		log.debug("removeAttivitaAbbonamento: " + req);
		Optional<Abbonamento> ab = abboR.findById(req.getAbbonamentoId());
		if (ab.isEmpty()) {
			throw new AcademyException("Abbonamento non trovato nel db: " +req.getAbbonamentoId());
		}
		Optional<Attivita> at = attiR.findById(req.getId());
		if (at.isEmpty()) {
			throw new AcademyException("Atività non trovata nel db: " +req.getAbbonamentoId());
		}
		ab.get().getAttivita().remove(at.get());
		abboR.save(ab.get());
	}*/
	
	@Override
	public void removeAttivitaAbbonamento(AttivitaReq req) throws AcademyException {
		Optional<Abbonamento> ab = abboR.findById(req.getAbbonamentoId());
		if (ab.isEmpty()) {
			throw new AcademyException("Abbonamento non trovato nel db: " +req.getAbbonamentoId());
		}
		ab.ifPresent(abObj -> 
						abObj.getAttivita().stream()
						.filter(a -> a.getId() == req.getId())
						.findFirst()
						.ifPresent(abObj.getAttivita()::remove)); //l'espressione getAttivita::remove è un method reference
																  //abObj.getAttivita().remove()
																  //è una scorciatoia per dire quando ho un oggetto Attivita
																  //passarlo al metodo remove() di quella lista
		if (ab.get().getAttivita().size() == 0) {
			log.debug("Delete: " + req);
			abboR.delete(ab.get());
		} else {
			abboR.save(ab.get());
		}

	}

}


