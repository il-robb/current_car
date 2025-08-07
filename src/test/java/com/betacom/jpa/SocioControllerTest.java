package com.betacom.jpa;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.betacom.jpa.controller.SocioController;
import com.betacom.jpa.dto.SocioDTO;
import com.betacom.jpa.requests.SocioReq;
import com.betacom.jpa.response.ResponseBase;
import com.betacom.jpa.response.ResponseList;
import com.betacom.jpa.response.ResponseObject;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SocioControllerTest {
	
	@Autowired
	private SocioController socC; 
	
	@Test
	@Order(1)
	public void listTest() {
		log.debug("Test list socio");
		
		ResponseList<SocioDTO> r = socC.list(null, null, "Brr Brr", null);
	
		Assertions.assertThat(r.getRc()).isEqualTo(true); //isRc() == getRc()
		Assertions.assertThat(r.getDati().get(0).getCognome()).isEqualTo("Patapim");
	}
	
	@Test
	@Order(2)
	public void getSocioTest() {
		log.debug("Test get socio");
		
		ResponseObject<SocioDTO> r = socC.getSocio(1);
	
		Assertions.assertThat(r.getRc()).isEqualTo(true);
		Assertions.assertThat(r.getDati().getCognome()).isEqualTo("Patapim");
	}
	
	@Test
	@Order(3)
	public void getSocioTestError() {
		log.debug("Test error get socio");
		
		ResponseObject<SocioDTO> r = socC.getSocio(69);
	
		Assertions.assertThat(r.getRc()).isEqualTo(false);
		Assertions.assertThat(r.getMsg()).isEqualTo("socio-not-exists");
	}
	
	@Test
	@Order(4)
	public void updateSocioTest() {
		log.debug("Test update socio");
		SocioReq req = new SocioReq();
		req.setId(2);
		req.setNome("Bombardino");
		req.setCodiceFiscale("BMNCD98QWP313A");
		
		ResponseBase r = socC.update(req);
		Assertions.assertThat(r.getRc()).isEqualTo(true);
		
		ResponseObject<SocioDTO> rO = socC.getSocio(2);
		Assertions.assertThat(r.getRc()).isEqualTo(true);
		Assertions.assertThat(rO.getDati().getNome()).isEqualTo("Bombardino");
		Assertions.assertThat(rO.getDati().getCodiceFiscale()).isEqualTo("BMNCD98QWP313A");
		
	}
	@Test
	@Order(5)
	public void createSocioTest() throws Exception {
		log.debug("Test create socio");
		SocioReq req = new SocioReq();
		req.setCodiceFiscale("TRRTRR98QWP313A");
		req.setCognome("Troppa Trippa");
		req.setNome("Trippi Troppi");
		req.setEmail("trptrp@gmail.com");
		ResponseBase r = socC.create(req);
		Assertions.assertThat(r.getRc()).isEqualTo(true);
		
		req = new SocioReq(); //new socio per test delete
		req.setCodiceFiscale("TTSHR98QWP313A");
		req.setCognome("Sahur");
		req.setNome("Tung Tung");
		req.setEmail("t.sahur@gmail.com");
		r = socC.create(req);
		Assertions.assertThat(r.getRc()).isEqualTo(true);
		
	}
	
	@Test
	@Order(6)
	public void deleteSocioTest() throws Exception {
		log.debug("Test delete socio");
		SocioReq req = new SocioReq();
		req.setId(3);
		ResponseBase r = socC.delete(req);
		Assertions.assertThat(r.getRc()).isEqualTo(true);
	}
	
	@Test
	@Order(7)
	public void deleteSocioTestError() throws Exception {
		log.debug("Test delete socio");
		SocioReq req = new SocioReq();
		req.setId(23);
		ResponseBase r = socC.delete(req);
		Assertions.assertThat(r.getRc()).isEqualTo(false);
	}
	
	
	
}
