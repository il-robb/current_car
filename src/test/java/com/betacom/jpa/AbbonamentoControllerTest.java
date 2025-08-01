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
import com.betacom.jpa.requests.AbbonamentoReq;
import com.betacom.jpa.requests.AttivitaReq;
import com.betacom.jpa.response.ResponseBase;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AbbonamentoControllerTest {
	
	@Autowired
	private AbbonamentoController abbC;
	@Autowired
	private AttivitaController attiC;
	
	@Test
	@Order(1)
	public void createAbbonamentoTest() {
		log.debug("test create abbonamento");
		
		AbbonamentoReq req = new AbbonamentoReq();
		req.setDataIscrizione(LocalDate.now());
		req.setSocioId(1);
		
		ResponseBase r = abbC.create(req);
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
		
	}
	//creare attivita e fare remove di attivita e di un abbonamento
}
