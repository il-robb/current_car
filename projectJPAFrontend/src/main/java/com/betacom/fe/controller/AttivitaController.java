package com.betacom.fe.controller;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.ModelAndView;

import com.betacom.fe.dto.AbbonamentoDTO;
import com.betacom.fe.request.AttivitaReq;
import com.betacom.fe.response.ResponseBase;
import com.betacom.fe.response.ResponseObject;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class AttivitaController {
	private WebClient clientWeb;

	public AttivitaController(WebClient clientWeb) {
		super();
		this.clientWeb = clientWeb;
	}
	
	@GetMapping("listAttivita")
	public ModelAndView listAttivita(@RequestParam Integer id) {
		log.debug("listAttivita:" + id);
		ModelAndView mav = new ModelAndView("listAbbonamenti");
		
		
		AbbonamentoDTO abb = clientWeb.get()
				.uri(uriBuilder -> uriBuilder
						.path("abbonamento/getAbbonamento")
						.queryParam("id", id)
						.build())
				.retrieve()
				.bodyToMono(new ParameterizedTypeReference<ResponseObject<AbbonamentoDTO>>() {})
				.block()
				.getDati();
		log.debug("socio abbo:" + abb.getAttivita().size() );
		
		abb.getAttivita().forEach(a -> log.debug(a.toString()));
		mav.addObject("abbonamentoId", id);
		mav.addObject("listAbb", abb.getAttivita());	
		return mav;
	}
	
	@GetMapping("createAttivita")
	public String createAttivita(@RequestParam Integer abbonamentoId,
	                             @RequestParam Integer socioId,
	                             @RequestParam Integer attivitaId) {
	   // log.debug("createAttivita - abbonamentoId: {}, attivitaId: {}", abbonamentoId, attivitaId);
	    
	    AttivitaReq req = new AttivitaReq();
	    req.setAbbonamentoId(abbonamentoId);
	    req.setId(attivitaId); 
	    
	   
	    
	    ResponseBase resp = clientWeb.post()
	            .uri("attivita/createAttivitaAbbonamento")
	            .contentType(MediaType.APPLICATION_JSON)
	            .bodyValue(req)
	            .retrieve()
	            .bodyToMono(ResponseBase.class)
	            .block();

	    log.debug("rc:" + resp.getRc());
	    
	    if (!resp.getRc()) { 
	    	req.setErrMessage("Attivita+gia+presente");
	    	return "redirect:/listAbbonamenti?id=" + socioId + "&errMessage=Attivita+gia+presente";
	    } else {
	    
	    return "redirect:/listAbbonamenti?id=" + socioId;
	    }
	}
	
	@PostMapping("deleteAttivita")
	public String deleteAttivita(
									@RequestParam Integer abbonamentoId,
									@RequestParam Integer socioId,
									@RequestParam Integer attivitaId) {
		
		
		AttivitaReq req = new AttivitaReq();
		req.setId(attivitaId);
		req.setAbbonamentoId(abbonamentoId);
		ResponseBase resp = clientWeb.post()
	            .uri("attivita/removeAttivitaAbbonamento")
	            .contentType(MediaType.APPLICATION_JSON)
	            .bodyValue(req)
	            .retrieve()
	            .bodyToMono(ResponseBase.class)
	            .block();
		
		return "redirect:/listAbbonamenti?id=" + socioId;
	}
}

