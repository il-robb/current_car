package com.betacom.jpa.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.jpa.dto.SocioDTO;
import com.betacom.jpa.services.interfaces.ISocioServices;

@RestController  //notazione per springboot, cercare cosa fa
@RequestMapping("/rest/socio")
public class SocioController {

	private ISocioServices socioS;
	
	
	public SocioController(ISocioServices socioS) {
		this.socioS = socioS;
	}


	@GetMapping("/list")
	public List<SocioDTO> test() {
		return socioS.listAll();
	}
}
