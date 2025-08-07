package com.betacom.fe.controller;

import java.time.LocalDate;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.ModelAndView;

import com.betacom.fe.dto.CertificatoDTO;
import com.betacom.fe.dto.SocioDTO;
import com.betacom.fe.request.CertificatoReq;
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
	public ModelAndView listCertificato(@RequestParam Integer id,
	                                    @RequestParam(required = false) String errMessage) {
	    log.debug("listCertificato: " + id);
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

	    mav.addObject("socioId", id);
	    mav.addObject("certificato", soc.getCertificato());

	    if (errMessage != null) {
	        mav.addObject("errMessage", errMessage);
	    }

	    return mav;
	}

	
	
	@GetMapping("createCertificato")
	public ModelAndView createCertificato(@RequestParam Integer socioId) {
	    log.debug("createCertificato");

	    // Recupero il socio per vedere se ha già un certificato
	    SocioDTO soc = clientWeb.get()
	            .uri(uriBuilder -> uriBuilder
	                    .path("socio/getSocio")
	                    .queryParam("id", socioId)
	                    .build())
	            .retrieve()
	            .bodyToMono(new ParameterizedTypeReference<ResponseObject<SocioDTO>>() {})
	            .block()
	            .getDati();

	    if (soc.getCertificato() != null) { //certificato già presente, redirect su listCertificato con il messaggio di errore e il socio id
	    	return new ModelAndView("redirect:/listCertificato?id=" + socioId + "&errMessage=Certificato+gia'+presente");
	    }


	    ModelAndView mav = new ModelAndView("createCertificato");
	    CertificatoReq req = new CertificatoReq();
	    req.setSocioId(socioId);
	    //req.setDataCertificato(LocalDate.now());
	    mav.addObject("certificato", req);
	    return mav;
	}

	
	@GetMapping("updateCertificato")
	public ModelAndView updateCertificato(@RequestParam Integer id) {
	    log.debug("updateCertificato socioId: " + id);

	    SocioDTO socioResp = clientWeb.get()
	        .uri(uriBuilder -> uriBuilder
	            .path("/socio/getSocio")
	            .queryParam("id", id)
	            .build())
	        .retrieve()
	        .bodyToMono(new ParameterizedTypeReference<ResponseObject<SocioDTO>>() {})
	        .block()
	        .getDati();

	    CertificatoDTO certDTO = socioResp.getCertificato();

	    CertificatoReq cert = CertificatoReq.builder()
	            .id(certDTO.getId())
	            .tipo(certDTO.getTipo())
	            .dataCertificato(certDTO.getDataCertificato())
	            .socioId(socioResp.getId())
	            .build();
	    

	    ModelAndView mav = new ModelAndView("createCertificato");
	    mav.addObject("certificato", cert);

	    return mav;
	}



	
	@PostMapping("saveCertificato")
	public Object saveCertificato(@ModelAttribute("certificato") CertificatoReq req) {
	    log.debug("saveCertificato: " + req);

	    String operation = (req.getId() == null) ? "create" : "update";

	    ResponseBase resp = null;

	    String uri = "certificato/" + operation;

	    if ("create".equalsIgnoreCase(operation)) {
	        resp = clientWeb.post()
	                .uri(uri)
	                .contentType(MediaType.APPLICATION_JSON)
	                .bodyValue(req)
	                .retrieve()
	                .bodyToMono(ResponseBase.class)
	                .block();
	    } else {
	        resp = clientWeb.put()
	                .uri(uri)
	                .contentType(MediaType.APPLICATION_JSON)
	                .bodyValue(req)
	                .retrieve()
	                .bodyToMono(ResponseBase.class)
	                .block();
	    }

	    log.debug(operation + " : " + resp.getRc());

	    if (!resp.getRc()) {
	        ModelAndView mav = new ModelAndView("createCertificato");
	        mav.addObject("certificato", req);
	        return mav;
	    }

	    return "redirect:/listCertificato?id=" + req.getSocioId();
	}

}
	
