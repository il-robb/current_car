package com.betacom.jpa.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.betacom.jpa.dto.CertificatoDTO;
import com.betacom.jpa.dto.SocioDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.models.Certificato;
import com.betacom.jpa.models.Socio;
import com.betacom.jpa.repositories.ICertificatoRepository;
import com.betacom.jpa.repositories.ISocioRepository;
import com.betacom.jpa.requests.CertificatoReq;
import com.betacom.jpa.services.interfaces.ICertificatoServices;

import lombok.extern.log4j.Log4j2;
@Log4j2
@Service
public class CertificatoImpl implements ICertificatoServices{

	
	private ICertificatoRepository certR;
	private ISocioRepository  socioR;
	

	public CertificatoImpl(ICertificatoRepository certR, ISocioRepository socioR) {
		super();
		this.certR = certR;
		this.socioR = socioR;
	}

	
	@Override
	public void create(CertificatoReq req) throws AcademyException {
		log.debug("create :" + req);
		
		Optional<Socio> soc = socioR.findById(req.getSocioId());
		if (soc.isEmpty())
			throw new AcademyException("Socio non trovato :" + req.getSocioId());
		
		Certificato cer = new Certificato();
		cer.setDataCertificato(req.getDataCertificato());
		cer.setTipo(req.getTipo());
		cer.setSocio(soc.get());     // load relation
		
		certR.save(cer);
		
	}


	@Override
	public List<SocioDTO> lisAll() {
		List<Certificato> lC = certR.findAll();
		return lC.stream()
				.map(c -> SocioDTO.builder().id(c.getSocio().getId())
						.cognome(c.getSocio().getCognome())
						.nome(c.getSocio().getNome())
						.codiceFiscale(c.getSocio().getCodiceFiscale())
						.email(c.getSocio().getEmail())
						.certificato(CertificatoDTO.builder().id(c.getId()).tipo(c.getTipo()).dataCertificato(c.getDataCertificato()).build())
						.build()).collect(Collectors.toList());
						
				
	}



}
