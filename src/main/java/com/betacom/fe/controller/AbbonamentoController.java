package com.betacom.fe.controller;

import java.time.LocalDate;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.ModelAndView;

import com.betacom.fe.dto.SocioDTO;
import com.betacom.fe.requests.AbbonamentoReq;
import com.betacom.fe.requests.AttivitaReq;
import com.betacom.fe.response.ResponseBase;
import com.betacom.fe.response.ResponseObject;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class AbbonamentoController {
	private WebClient clientWeb;

	public AbbonamentoController(WebClient clientWeb) {
		this.clientWeb = clientWeb;
	}
	
	@GetMapping("listAbbonamenti")
	public ModelAndView listAbbonamenti(@RequestParam Integer id) {
		log.debug("listAbbonamenti:" + id);
		ModelAndView mav = new ModelAndView("listAbbonamenti");
		AttivitaReq att = new AttivitaReq();
		
		SocioDTO soc = clientWeb.get()
				.uri(uriBuilder -> uriBuilder
						.path("socio/getSocio")
						.queryParam("id", id)
						.build())
				.retrieve()
				.bodyToMono(new ParameterizedTypeReference<ResponseObject<SocioDTO>>() {})
				.block()
				.getDati();
		log.debug("socio abbo:" + soc.getAbbonamento().size() );

		soc.getAbbonamento().forEach(a -> log.debug(a.toString()));
		mav.addObject("attivita", att);
		mav.addObject("socioId",id);
		mav.addObject("listAbbonamenti",soc.getAbbonamento());
		return mav;
	}
	
	@GetMapping("createAbbonamento")
	public String createAbbonamento(@RequestParam Integer socioId) {
		log.debug("CreateAbbonamento e stampa il parametro socioId: " + socioId);
		AbbonamentoReq r = new AbbonamentoReq();
		r.setSocioId(socioId);
		r.setDataIscrizione(LocalDate.now());
		
		ResponseBase resp = clientWeb.post()
				.uri("abbonamento/create")	
	            .contentType(MediaType.APPLICATION_JSON)
	            .bodyValue(r)
				.retrieve()
				.bodyToMono(ResponseBase.class)
				.block();
		
		log.debug("Rc di crea abboanmenti"+resp.getRc());
		
		return "redirect:/listAbbonamenti?id=" + socioId;
	}

	
	
}