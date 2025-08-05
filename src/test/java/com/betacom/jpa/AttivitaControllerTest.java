package com.betacom.jpa;

import java.math.BigDecimal;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.betacom.jpa.controller.AttivitaController;
import com.betacom.jpa.dto.AttivitaDTO;
import com.betacom.jpa.requests.AttivitaReq;
import com.betacom.jpa.response.ResponseBase;
import com.betacom.jpa.response.ResponseList;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Rollback(value = false)
public class AttivitaControllerTest {
	@Autowired
	private AttivitaController attiC;

	@Test
	@Order(1)
	public void createAttivitaTest() {
		log.debug("test create attività");
		
		AttivitaReq req = new AttivitaReq();
		req.setDescrizione("Yoga");
		req.setPrezzo(new BigDecimal(10));
		ResponseBase r = attiC.create(req);
		Assertions.assertThat(r.getRc()).isEqualTo(true);
		
		req = new AttivitaReq();
		req.setDescrizione("Ciclismo");
		req.setPrezzo(new BigDecimal(20));
		r = attiC.create(req);
		Assertions.assertThat(r.getRc()).isEqualTo(true);
		
		req = new AttivitaReq();
		req.setDescrizione("Nuoto");
		req.setPrezzo(new BigDecimal(15));
		r = attiC.create(req);
		Assertions.assertThat(r.getRc()).isEqualTo(true);
	}
	
	@Test
	@Order(2)
	public void listAttivitaTest() {
		log.debug("test list attività");
		ResponseList<AttivitaDTO> r = attiC.list();
		r.getDati().forEach(s -> s.toString());
		Assertions.assertThat(r.getRc()).isEqualTo(true);
		Assertions.assertThat(r.getDati().size()).isEqualTo(3);
	}
}