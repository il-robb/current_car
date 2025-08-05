package com.betacom.jpa.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	@Transactional(rollbackFor = Exception.class)
	@Override
	public int create(CertificatoReq req) throws AcademyException {
		log.debug("create :" + req);
		
		Optional<Socio> soc = socioR.findById(req.getSocioId());
		if (soc.isEmpty())
			throw new AcademyException("Socio non trovato :" + req.getSocioId());
		
		Certificato cer = new Certificato();
		cer.setDataCertificato(req.getDataCertificato());
		cer.setTipo(req.getTipo());
		cer.setSocio(soc.get());     // load relation
		
		return certR.save(cer).getId();
		
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void update(CertificatoReq req) throws AcademyException {
		log.debug("update :" + req);
		Optional<Certificato> c = certR.findById(req.getId());
		if (c.isEmpty())
			throw new AcademyException("Certificato non trovato :" + req.getId());

		Certificato certif = c.get();
		if (req.getTipo() != null) {
			certif.setTipo(req.getTipo());
		}
		if (req.getDataCertificato() != null) {
			certif.setDataCertificato(req.getDataCertificato());
		}
		certR.save(certif);
	}

	
	@Override
	public List<SocioDTO> listAll() {
		List<Certificato> lC = certR.findAll();
		return lC.stream()
				.map(c -> SocioDTO.builder()
						.id(c.getSocio().getId())
						.cognome(c.getSocio().getCognome())
						.nome(c.getSocio().getNome())
						.codiceFiscale(c.getSocio().getCodiceFiscale())
						.email(c.getSocio().getEmail())
						.certificato(CertificatoDTO.builder()
								.id(c.getId())
								.dataCertificato(c.getDataCertificato())
								.tipo(c.getTipo())
								.build()
								)
						.build())
				.collect(Collectors.toList());
	}









}
