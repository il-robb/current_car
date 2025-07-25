package com.betacom.jpa.process;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Component;

import com.betacom.jpa.dto.SocioDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.requests.CertificatoReq;
import com.betacom.jpa.requests.SocioReq;
import com.betacom.jpa.services.interfaces.ICertificatoServices;
import com.betacom.jpa.services.interfaces.ISocioServices;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class MainProcess {

	private ISocioServices socioS;
	private ICertificatoServices certifS;

	public MainProcess(ISocioServices socioS, ICertificatoServices certifS) {
		this.socioS = socioS;
		this.certifS = certifS;
	}

	
	public void Execute() {
	
		try {
			SocioReq r = new SocioReq();
			r.setNome("Paolo");
			r.setCognome("Rossi");
			r.setCodiceFiscale("123456789");
			r.setEmail("p.rossi@t.it");
		
			int socioId = socioS.insert(r);
			CertificatoReq cR = new CertificatoReq();
			cR.setDataCertificato(LocalDate.parse("2025-01-01"));
			cR.setSocioId(socioId);
			cR.setTipo(false);
			certifS.create(cR);
		} catch (AcademyException e) {
			log.error(e.getMessage());
		}
		
		
		List<SocioDTO> lS = socioS.listAll();
		lS.forEach(s -> log.debug(s.toString()));
		log.debug("**** partendo da certificato ******");
		List<SocioDTO> lC = certifS.lisAll();
		lC.forEach(s -> log.debug(s.toString()));
		
		log.debug("Fine lavoro");
		
	}

	
}
