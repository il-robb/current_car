package com.betacom.jpa.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.jpa.dto.AttivitaDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.models.Abbonamento;
import com.betacom.jpa.models.Attivita;
import com.betacom.jpa.repositories.IAbbonamentoRepository;
import com.betacom.jpa.repositories.IAttivitaRepository;
import com.betacom.jpa.requests.AttivitaReq;
import com.betacom.jpa.services.interfaces.IAttivitaService;

@Service
public class AttivitaImpl implements IAttivitaService{


	private IAbbonamentoRepository abboR;
	private IAttivitaRepository attR;

	public AttivitaImpl(IAbbonamentoRepository abboR,IAttivitaRepository attR) {
		this.abboR = abboR;
		this.attR = attR;
	}

	@Override
	public void create(AttivitaReq req) throws AcademyException {
		Optional<Attivita> s = attR.findByDescrizione(req.getDescrizione());
	    if (s.isPresent())
            throw new AcademyException("Attivita gia esistente in database : " + req.getDescrizione());
	    
		Attivita att = new Attivita();
		att.setDescrizione(req.getDescrizione());
		att.setPrezzo(req.getPrezzo());
		
		attR.save(att);
	}

	@Override
	public void update(AttivitaReq req) throws AcademyException {
		Optional<Attivita> s = attR.findById(req.getAttivitaId());
	    if (s.isEmpty())
            throw new AcademyException("Attivita non trovato in database : " + req.getAttivitaId());
	    
        Attivita att = s.get();
        if(req.getDescrizione() != null) {
        	s = attR.findByDescrizione(req.getDescrizione());
    	    if (s.isPresent())
                throw new AcademyException("Attivita gia esistente in database : " + req.getDescrizione());
    	    
            att.setDescrizione(req.getDescrizione());
        }
        
        if(req.getPrezzo() != null) {
            att.setPrezzo(req.getPrezzo());
        }

        attR.save(att);
		
	}

	@Override
	public void delete(AttivitaReq req) throws AcademyException {
		Optional<Attivita> s = attR.findById(req.getAttivitaId());
		
		if (s.isEmpty())
			throw new AcademyException("Attivita non trovatoin database");
		
		attR.delete(s.get());
	}

	@Override
	public List<AttivitaDTO> listAll() {
		List<Attivita> lC = attR.findAll();
		return lC.stream()
				.map(c -> AttivitaDTO.builder()
						.idAttivita(c.getId())
						.descrizione(c.getDescrizione())
						.prezzo(c.getPrezzo())
						.build()).collect(Collectors.toList());
						
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void creaAttivitaAbbonamento(AttivitaReq req) throws AcademyException {
		Optional<Attivita> s = attR.findById(req.getAttivitaId());
	    if (s.isEmpty())
            throw new AcademyException("Attivita non trovato in database : " + req.getAttivitaId());
	    Optional<Abbonamento> a = abboR.findById(req.getAbbonamentoId());
	    if (a.isEmpty())
            throw new AcademyException("abbonamento non trovato in database : " + req.getAttivitaId());
	    
	    a.get().getAttivita().add(s.get());
	    abboR.save(a.get());
	    
	    
	    
		
	}


}
