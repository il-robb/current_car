package com.betacom.fe.controller;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.reactive.function.client.WebClient;

import com.betacom.fe.dto.AttivitaDTO;
import com.betacom.fe.dto.SocioDTO;
import com.betacom.fe.requests.AttivitaReq;
import com.betacom.fe.response.ResponseBase;
import com.betacom.fe.response.ResponseObject;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class AttivitaController {

	private WebClient clientWeb;

	public AttivitaController(WebClient clientWeb) {
		this.clientWeb = clientWeb;
	}

	@PostMapping("saveAttivita")
	public String saveAttivita(@ModelAttribute("attivita") AttivitaReq req) {
		log.debug("Questo è saveATTIVITA : " + req);		
		
		ResponseBase attivitaAbbo = clientWeb.post()
				.uri("attivita/createAttivitaAbbonamento")	
	            .contentType(MediaType.APPLICATION_JSON)
	            .bodyValue(req)
				.retrieve()
				.bodyToMono(ResponseBase.class)
				.block();
		
		log.debug("Rc di crea attivitaAbbo"+attivitaAbbo.getRc());
		
		return "redirect:/listAbbonamenti?id=" + req.getAbbonamentoId();
		
	}
	
	@DeleteMapping("deleteAttivita")
	public String deleteAttivita(@ModelAttribute)
}
