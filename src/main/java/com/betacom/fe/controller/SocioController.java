package com.betacom.fe.controller;

import java.util.Optional;

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
import com.betacom.fe.request.SocioReq;
import com.betacom.fe.response.ResponseBase;
import com.betacom.fe.response.ResponseList;
import com.betacom.fe.response.ResponseObject;

import lombok.extern.log4j.Log4j2;
@Log4j2
@Controller
public class SocioController {
	
	private WebClient clientWeb;
	
	public SocioController(WebClient clientWeb) {
		super();
		this.clientWeb = clientWeb;
	}
	
	

	@GetMapping(value={"/", "listSocio", "home"})
	public ModelAndView listSocio(
			@RequestParam(required = false) Integer id,
			@RequestParam(required = false) String codiceFiscale,
			@RequestParam(required = false) String nome,
			@RequestParam(required = false) String cognome,
			@RequestParam(required = false) String attivita,
			@RequestParam(required = false) String email
			) {
		ModelAndView mav = new ModelAndView("listSocio");
		
		/*log.debug("nome: " + nome + " cognome: " +cognome);
		log.debug("listSocio params -> id: {}, codiceFiscale: {}, cognome: {}, nome: {}, attivita: {}, email: {}",
		           id, codiceFiscale, cognome, nome, attivita, email);*/

		
		ResponseList<?> lSoc = clientWeb.get()
				.uri(UriBuilder -> UriBuilder
						.path("/socio/listByFilter")
						.queryParamIfPresent("id", Optional.ofNullable(id))
						.queryParamIfPresent("codiceFiscale", Optional.ofNullable(codiceFiscale))
						.queryParamIfPresent("nome", Optional.ofNullable(cognome))
						.queryParamIfPresent("cognome", Optional.ofNullable(nome))
						.queryParamIfPresent("attivita", Optional.ofNullable(attivita))
						.queryParamIfPresent("email", Optional.ofNullable(email))
						.build())
				.retrieve()
				.bodyToMono(ResponseList.class)
				.block();
		
		log.debug("Response rc: " +lSoc.getRc());
		
		ResponseList<?> atti = clientWeb.get()
				.uri("attivita/list")
				.retrieve()
				.bodyToMono(ResponseList.class)
				.block();
		
		log.debug("Response attivita rc: " +atti.getRc());
		
		mav.addObject("listAttivita", atti);
		mav.addObject("param", new SocioReq());
		mav.addObject("listSocio", lSoc.getDati());
		
		return mav;
	}
	
	@GetMapping("createSocio")
	public ModelAndView createSocio() {
		ModelAndView mav = new ModelAndView("createSocio");
		SocioReq r = new SocioReq();
		r.setErrorMsg(null);
		mav.addObject("socio", r);
		return mav;
	}
	
	@GetMapping("updateSocio")
	public ModelAndView updateSocio(@RequestParam(required=true) Integer id) {
		log.debug("updateSocio: " +id);
		
		SocioDTO resp = clientWeb.get()
				.uri(uriBuilder -> uriBuilder
						.path("socio/getSocio")
						.queryParam("id", id)
						.build())
				.retrieve()
				.bodyToMono(new ParameterizedTypeReference<ResponseObject<SocioDTO>>() {})
				.block()
				.getDati();
		
		log.debug("Response: " + resp.getCognome() + " " + resp.getNome());
		
		SocioReq s = SocioReq.builder()
				.id(resp.getId())
				.cognome(resp.getCognome())
				.nome(resp.getNome())
				.codiceFiscale(resp.getCodiceFiscale())
				.email(resp.getEmail())
				.errorMsg(null)
				.build();
		
		ModelAndView mav = new ModelAndView("createSocio");
		mav.addObject("socio", s);
		
		return mav;
				
	}
	
	@PostMapping("saveSocio")
	public Object saveSocio(@ModelAttribute("socio") SocioReq req) {
		log.debug("saveSocio: " +req);
		
		String operation = (req.getId() == null) ? "create" : "update";
		
		ResponseBase resp = null;
		
		String uri = "socio/" + operation;
		
		if("create".equalsIgnoreCase(operation)) {
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
			ModelAndView mav = new ModelAndView("createSocio");
			req.setErrorMsg(resp.getMsg());
			mav.addObject("socio", req);
			return mav;
			
		}
		
		return "redirect:/listSocio";
	}
}


