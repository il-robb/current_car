package com.betacom.jpa;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.betacom.jpa.dto.SocioDTO;
import com.betacom.jpa.requests.SocioReq;
import com.betacom.jpa.services.interfaces.ISocioServices;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SocioServiceTest {
	@Autowired
	private ISocioServices socS;
	
	@Test
	@Order(1)
	public void createSocioTest() throws Exception {
		
		SocioReq req = new SocioReq();
		req.setCodiceFiscale("BRRBRR98QWP313A");
		req.setCognome("Patapim");
		req.setNome("Brr Brr");
		req.setEmail("brrbrrpatapim@gmail.com");
		
		socS.insert(req);
		
		List<SocioDTO> lS = socS.list(null, null, null, null);
		
		SocioDTO createSocio = lS.stream()
				.filter(s -> "BRRBRR98QWP313A".equals(s.getCodiceFiscale()))
				.findFirst()
				.orElseThrow(() -> new AssertionError("Socio non trovato :( "));
		
		Assertions.assertThat(createSocio.getCognome()).isEqualTo("Patapim");
		
		req = new SocioReq();
		req.setCodiceFiscale("BMBCD98QWP313A");
		req.setCognome("Crocodilo");
		req.setNome("Bombardiro");
		req.setEmail("bombardirococodrilo@gmail.com");
		
		socS.insert(req);
		lS = socS.list(null, null, null, null);
		
		lS.forEach(s -> log.debug(s.toString()));
	}
	
	/*@Test   //con questo testiamo l'errore, socio duplicato
	@Order(2)
	public void createSocioErrorTest() throws Exception{
		SocioReq req = new SocioReq();
		req.setCodiceFiscale("BMBCRD98QWP313A");
		req.setCognome("Crocodilo");
		req.setNome("Bombardiro");
		req.setEmail("bombardirococodrilo@gmail.com");
		
		assertThrows(Exception.class, () -> {
			socS.insert(req);
		});
	}*/
}
