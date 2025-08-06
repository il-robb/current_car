package com.betacom.jpa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class AbbonamentoController {

private WebClient clientWeb;
	

	public AbbonamentoController(WebClient clientWeb) {
		super();
		this.clientWeb = clientWeb;
	}
	
	@GetMapping("listAbbonamenti")
	public ModelAndView listAbbonamento(@RequestParam Integer id)
	{
		ModelAndView mav = new ModelAndView("listAbbonamento"); 
		
		return mav;
	}

}
