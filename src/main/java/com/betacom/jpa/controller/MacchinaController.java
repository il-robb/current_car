package com.betacom.jpa.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.jpa.services.interfaces.IMacchinaServices;


@RestController
@RequestMapping("/rest/macchina")
public class MacchinaController {
	private IMacchinaServices CarS;

	public MacchinaController(IMacchinaServices carS) {
		super();
		CarS = carS;
	}
	
	
}
