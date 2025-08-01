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

	public void execute() {
		
		try {
			SocioReq r = new SocioReq();
			r.setNome("Paolo");
			r.setCognome("Rossi");
			r.setCodiceFiscale("123456789");
			r.setEmail("p.rossi@gmail.com");
			
			int socioId = socioS.insert(r);
			CertificatoReq cR = new CertificatoReq();
			cR.setDataCertificato(LocalDate.parse("2025-07-22"));
			cR.setSocioId(socioId);
			cR.setTipo(false);
			certifS.create(cR);
			
			r = new SocioReq();
			r.setNome("Bruno");
			r.setCognome("Verde");
			r.setCodiceFiscale("99999999");
			r.setEmail("b.verde@gmail.com");
			
			socioId = socioS.insert(r);
			cR = new CertificatoReq();
			cR.setDataCertificato(LocalDate.parse("2024-02-12"));
			cR.setSocioId(socioId);
			cR.setTipo(true);
			certifS.create(cR);
			
			r = new SocioReq();
			r.setNome("Alberto");
			r.setCognome("Nero");
			r.setCodiceFiscale("888888888");
			r.setEmail("a.nero@gmail.com");
			
			socioId = socioS.insert(r);
			cR = new CertificatoReq();
			cR.setDataCertificato(LocalDate.parse("2024-05-02"));
			cR.setSocioId(socioId);
			cR.setTipo(true);
			int certifId = certifS.create(cR);
			
			/*
			r = new SocioReq();
			r.setId(socioId);
			
			socioS.delete(r);
			System.out.println("After delete socio");*/
			
			cR = new CertificatoReq();
			cR.setId(certifId);
			certifS.delete(cR);
			System.out.println("After delete certificato");
			
			
		} catch (AcademyException e) {
			log.error(e.getMessage());
		}
		
		List<SocioDTO> ls = socioS.listAll();
		ls.forEach(s -> log.debug(s.toString()));
		/*
		log.debug("********** partendo da certificato **********");
		List<SocioDTO> lc = certifS.listAll();
		lc.forEach(c -> log.debug(c.toString()));*/
		log.debug("Fine Lavoro");
	}

}
