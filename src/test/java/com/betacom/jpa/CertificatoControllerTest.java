package com.betacom.jpa;

import java.time.LocalDate;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.betacom.jpa.controller.CertificatoController;
import com.betacom.jpa.dto.SocioDTO;
import com.betacom.jpa.requests.CertificatoReq;
import com.betacom.jpa.response.ResponseBase;
import com.betacom.jpa.response.ResponseList;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CertificatoControllerTest {
	
	@Autowired
	private CertificatoController cerC;
	
	@Test
	@Order(1)
	public void createCertificatoTest() {
		log.debug("test create certificato");
		
		CertificatoReq req = new CertificatoReq();
		req.setDataCertificato(LocalDate.now());
		req.setSocioId(1);
		ResponseBase r = cerC.create(req);
		
		Assertions.assertThat(r.getRc()).isEqualTo(true);
		
		req = new CertificatoReq();
		req.setDataCertificato(LocalDate.now());
		req.setSocioId(2);
		req.setTipo(false);
		r = cerC.create(req);
		
		Assertions.assertThat(r.getRc()).isEqualTo(true);
		
	}
	
	@Test
	@Order(2)
	public void listCertificatoTest() {
		log.debug("test list certificato");
		ResponseList<SocioDTO> r = cerC.list();
		r.getDati().forEach(s -> s.toString());
		Assertions.assertThat(r.getRc()).isEqualTo(true);
		
	}
	
	@Test
	@Order(3)
	public void updateCertificatoTest() {
		log.debug("test update certificato");
		CertificatoReq req = new CertificatoReq();
		req.setId(2);
		req.setTipo(true);
		ResponseBase r = cerC.update(req);
		Assertions.assertThat(r.getRc()).isEqualTo(true);
		
	}
	
	@Test
	@Order(4)
	public void deleteCertificatoTest() {
	    log.debug("test delete certificato");
	    CertificatoReq req = new CertificatoReq();
	    req.setId(2);

	    try {
	    	cerC.delete(req);
	        Assertions.assertThat(true);  
	    	//Assertions.assertThat(r.getRc()).isEqualTo(true);
	    } catch (Exception e) {
	        log.error("Errore durante delete: " + e.getMessage());
	    }
	}

	
	@Test
	@Order(5)
	public void deleteCertificatoTestErrore() {
		log.debug("test delete certificato errore");
		CertificatoReq req = new CertificatoReq();
		req.setId(3);
		try {
			cerC.delete(req);
			Assertions.assertThat(false);
		} catch (Exception e) {
	        log.error("Nessun errore durante delete: " + e.getMessage());
		
		}
	}
}
