package com.betacom.jpa.process;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.type.descriptor.java.LocalDateJavaType;
import org.springframework.stereotype.Component;

import com.betacom.jpa.dto.SocioDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.requests.CertificatoReq;
import com.betacom.jpa.requests.SocioReq;
import com.betacom.jpa.services.interfaces.ICertificatoService;
import com.betacom.jpa.services.interfaces.ISocioService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class MainProcess {
	
	private ISocioService socioSer;
	private ICertificatoService certificatoSer;
	
	public MainProcess(ISocioService socioSer, ICertificatoService certificatoSer) {
		this.socioSer = socioSer;
		this.certificatoSer = certificatoSer;
	}
	
	public void Execute() {
//		try {
//			SocioReq r = new SocioReq();
//			r.setNome("Luca");
//			r.setCognome("Saporiti");
//			r.setCodiceFiscale("LSP345SPA");
//			r.setEmail("l.sapo@proton.me");
//			int socioId = socioSer.insert(r);
//			CertificatoReq req = new CertificatoReq();
//			req.setDataCertificato(LocalDate.parse("2025-07-22"));
//			req.setSocioId(socioId);
//			req.setTipo(false);
//			certificatoSer.insert(req);
//		} catch (AcademyException e) {
//			log.error(e.getMessage());
//		}
		List<SocioDTO> lS;
		try {
			lS = certificatoSer.listAll();
			lS.forEach(s->log.debug(s.toString()));
		} catch (AcademyException e) {
			e.printStackTrace();
		}
		log.debug("Ending project");
	}
}
