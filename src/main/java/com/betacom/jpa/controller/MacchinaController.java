package com.betacom.jpa.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.jpa.services.interfaces.IMacchinaService;

@RestController
@RequestMapping("/rest/macchina")
public class MacchinaController {
	private IMacchinaService CarS;

	public MacchinaController(IMacchinaService carS) {
		super();
		CarS = carS;
	}
	
	
}
