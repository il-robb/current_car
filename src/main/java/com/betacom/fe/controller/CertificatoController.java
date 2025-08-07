 package com.betacom.fe.controller;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.ModelAndView;

import com.betacom.fe.dto.SocioDTO;
import com.betacom.fe.requests.CertificatoReq;
import com.betacom.fe.requests.SocioReq;
import com.betacom.fe.response.ResponseBase;
import com.betacom.fe.response.ResponseObject;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class CertificatoController {

	private WebClient clientWeb;

	public CertificatoController(WebClient clientWeb) {
		this.clientWeb = clientWeb;
	}
	
	@GetMapping("listCertificato")
	public ModelAndView listCertificato(@RequestParam Integer id) {
		log.debug("listCertificato id: " + id);
		ModelAndView mav = new ModelAndView("listCertificato");
		
		SocioDTO soc = clientWeb.get()
				.uri(uriBuilder -> uriBuilder
						.path("socio/getSocio")
						.queryParam("id", id)
						.build())
				.retrieve()
				.bodyToMono(new ParameterizedTypeReference<ResponseObject<SocioDTO>>() {})
				.block()
				.getDati();
		log.debug("socio CERTIFICATO:" + soc.getCertificato());
		
		mav.addObject("socioId",id);
		mav.addObject("listCertificato",soc.getCertificato());	
		return mav;
		}
	
	// prof crea un @getMapping con listCertificato e utilizz< il SOcioDTo e poi il CertificatoReq
	
	@GetMapping("createCertificato")
	public ModelAndView createCertificato(@RequestParam Integer id) {
		log.debug("listCertificato id: " + id);
		ModelAndView mav = new ModelAndView("createCertificato");
		CertificatoReq c = new CertificatoReq();
		c.setSocioId(id);
		mav.addObject("certificato", c);
		mav.addObject("socioId",id);
		return mav;
		}
	
	@PostMapping("saveCertificato")
	public String saveCertificato(@ModelAttribute("certificato") CertificatoReq req) {
		log.debug("saveCertificato e stampa il req: " + req);
				
		if(req.getTipo().equalsIgnoreCase("agonistico")) {
			req.setTipo("true");
		}
		else {
			req.setTipo("false");
		};
		
		ResponseBase resp = clientWeb.post()
				.uri("certificato/create")	
	            .contentType(MediaType.APPLICATION_JSON)
	            .bodyValue(req)
				.retrieve()
				.bodyToMono(ResponseBase.class)
				.block();
		
		log.debug("Rc di crea certificato"+resp.getRc());
		
		return "redirect:/listCertificato?id=" + req.getSocioId();
	}
	
}
