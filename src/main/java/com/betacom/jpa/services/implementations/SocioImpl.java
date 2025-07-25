package com.betacom.jpa.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.betacom.jpa.dto.CertificatoDTO;
import com.betacom.jpa.dto.SocioDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.models.Socio;
import com.betacom.jpa.repositories.ISocioRepository;
import com.betacom.jpa.requests.SocioReq;
import com.betacom.jpa.services.interfaces.ISocioServices;
import com.betacom.jpa.utilities.Utilities;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class SocioImpl extends Utilities implements ISocioServices{
	
	private ISocioRepository socioR;

	public SocioImpl(ISocioRepository socioR) {
		this.socioR = socioR;
	}

	
	@Override
	public Integer insert(SocioReq req) throws AcademyException{
		log.debug("insert :" + req);
		Socio soc = new Socio();
		Optional<Socio> s = socioR.findByCodiceFiscale(req.getCodiceFiscale());
		
		if (s.isPresent())
			throw new AcademyException("Socio esistente in database");
		
		
		soc.setCodiceFiscale(req.getCodiceFiscale());
		soc.setCognome(req.getCognome());
		soc.setEmail(req.getEmail());
		soc.setNome(req.getNome());
		
		return socioR.save(soc).getId();	
		
	}
	
	@Override
	public void delete(SocioReq req) throws AcademyException {
		log.debug("delete :" + req);
		Optional<Socio> s = socioR.findById(req.getId());
		
		if (s.isEmpty())
			throw new AcademyException("Socio non trovatoin database");
		
		socioR.delete(s.get());
	}
	
	@Override
	public void update(SocioReq req) throws AcademyException {
		log.debug("update :" + req);
		Optional<Socio> s = socioR.findById(req.getId());
	    if (s.isEmpty())
            throw new AcademyException("Socio non trovatoin database : " + req.getId());
        Socio soc = s.get();
        if(req.getNome() != null) {
            soc.setNome(req.getNome());
        }
        if(req.getCognome() != null) {
            soc.setCognome(req.getCognome());
        }
        if(req.getEmail() != null) {
            soc.setEmail(req.getEmail());
        }
        if(req.getCodiceFiscale() != null) {
            Optional<Socio> sCF = socioR.findByCodiceFiscale(req.getCodiceFiscale());
            if (sCF.isPresent()) {
                throw new AcademyException("Codice fiscale esistente in database");
                }
            soc.setCodiceFiscale(req.getCodiceFiscale());
        }

        socioR.save(soc);
    }


	@Override
    public List<SocioDTO> listAll(){
        List<Socio> ls = socioR.findAll();
        return ls.stream()
                .map(s -> SocioDTO.builder()
                        .id(s.getId())
                        .cognome(s.getCognome())
                        .nome(s.getNome())
                        .codiceFiscale(s.getCodiceFiscale())
                        .email(s.getEmail())
                        .certificato((s.getCertificato() == null) ? null : CertificatoDTO.builder()
                                    .id(s.getCertificato().getId())
                                    .tipo(s.getCertificato().getTipo())
                                    .dataCertificato(s.getCertificato().getDataCertificato())
                                    .build())
                        .abbonamento(buildAbbonamento(s.getAbbonamento()))
                        .build()).collect(Collectors.toList());
        }


	@Override
	public SocioDTO getSocio(Integer id) throws AcademyException {
		Optional<Socio> a = socioR.findById(id);
			if(a.isEmpty()) throw new AcademyException("socio non esistente ");
			Socio s = a.get();
			
			return SocioDTO.builder()
					.id(s.getId())
                    .cognome(s.getCognome())
                    .nome(s.getNome())
                    .codiceFiscale(s.getCodiceFiscale())
                    .email(s.getEmail())
                    .certificato((s.getCertificato() == null) ? null : CertificatoDTO.builder()
                                .id(s.getCertificato().getId())
                                .tipo(s.getCertificato().getTipo())
                                .dataCertificato(s.getCertificato().getDataCertificato())
                                .build())
                    .abbonamento(buildAbbonamento(s.getAbbonamento()))
                    .build();
	}



}
