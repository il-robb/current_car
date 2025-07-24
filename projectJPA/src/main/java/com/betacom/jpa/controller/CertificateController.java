package com.betacom.jpa.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.jpa.dto.SocioDTO;
import com.betacom.jpa.services.interfaces.ICertificatoServices;

@RestController  //notazione per springboot, cercare cosa fa
@RequestMapping("/rest/certificato")
public class CertificateController {

	private ICertificatoServices CertS;
	
	


	public CertificateController(ICertificatoServices certS) {
		super();
		CertS = certS;
	}

	@GetMapping("/list")
	public List<SocioDTO> test() {
		return CertS.lisAll();
	}
}