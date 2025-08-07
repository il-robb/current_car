package com.betacom.jpa;

import java.time.LocalDate;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.betacom.jpa.controller.AbbonamentoController;
import com.betacom.jpa.controller.AttivitaController;
import com.betacom.jpa.controller.SocioController;
import com.betacom.jpa.dto.SocioDTO;
import com.betacom.jpa.requests.AbbonamentoReq;
import com.betacom.jpa.requests.AttivitaReq;
import com.betacom.jpa.response.ResponseBase;
import com.betacom.jpa.response.ResponseList;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AbbonamentoControllerTest {
	
	@Autowired
	private AbbonamentoController abbC;
	@Autowired
	private AttivitaController attiC;
	@Autowired
	private SocioController socC; 
	
	@Test
	@Order(1)
	public void createAbbonamentoTest() {
		log.debug("test create abbonamento");
		
		AbbonamentoReq req = new AbbonamentoReq();
		req.setDataIscrizione(LocalDate.now());
		req.setSocioId(1);
		
		ResponseBase r = abbC.create(req);
		Assertions.assertThat(r.getRc()).isEqualTo(true);
		
		req = new AbbonamentoReq();
		req.setDataIscrizione(LocalDate.now());
		req.setSocioId(2);
		r = abbC.create(req);
		Assertions.assertThat(r.getRc()).isEqualTo(true);
		
	}
	
	@Test
	@Order(2)
	public void createAttivitaAbbonamentoTest() {
		log.debug("test create attivita per abbonamento");
		AttivitaReq req = new AttivitaReq();
		req.setAbbonamentoId(1);
		req.setId(2); //set attivita
		ResponseBase r = attiC.createAttivitaAbbonamento(req);
		Assertions.assertThat(r.getRc()).isEqualTo(true);
		
		req = new AttivitaReq();
		req.setAbbonamentoId(2);
		req.setId(1); //set attivita
		r = attiC.createAttivitaAbbonamento(req);
		Assertions.assertThat(r.getRc()).isEqualTo(true);
		
	}
	
	@Test
	@Order(3)
	public void listByAttivitaTest() throws Exception {
		log.debug("Test list socio attivita");
		ResponseList<SocioDTO> r = socC.listByAttivita("Ciclismo");
		Assertions.assertThat(r.getRc()).isEqualTo(true);
		Assertions.assertThat(r.getDati()).isNotNull();
		Assertions.assertThat(r.getDati()).isNotEmpty(); 
	}
	
	@Test
	@Order(4)
	public void listByAttivitaTestError() throws Exception {
		log.debug("Test list socio attivita errore");
		ResponseList<SocioDTO> r = socC.listByAttivita("Karate");
		Assertions.assertThat(r.getRc()).isEqualTo(true); // <-- non è un errore tecnico
		Assertions.assertThat(r.getDati()).isEmpty();     // <-- lista vuota
	}
	
	@Test
	@Order(5)
	public void deleteAttivitaAbbonamentoTest() {
		log.debug("test delete attivita per abbonamento");
		AttivitaReq req = new AttivitaReq();
		req.setAbbonamentoId(1);
		req.setId(2);
		
		ResponseBase r = attiC.removeAttivitaAbbonamento(req);
		Assertions.assertThat(r.getRc()).isEqualTo(true);
	}
	
	@Test
	@Order(6)
	public void deleteAttivitaAbbonamentoTestError() {
		log.debug("test errore delete attivita per abbonamento");
		AttivitaReq req = new AttivitaReq();
		req.setAbbonamentoId(5);
		req.setId(6);
		
		ResponseBase r = attiC.removeAttivitaAbbonamento(req);
		Assertions.assertThat(r.getRc()).isEqualTo(false);
	}
	
}
