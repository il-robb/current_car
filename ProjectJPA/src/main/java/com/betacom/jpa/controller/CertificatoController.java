package com.betacom.jpa.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.jpa.dto.SocioDTO;
import com.betacom.jpa.requests.CertificatoReq;
import com.betacom.jpa.requests.SocioReq;
import com.betacom.jpa.response.ResponseBase;
import com.betacom.jpa.response.ResponseList;
import com.betacom.jpa.services.interfaces.ICertificatoService;
import com.betacom.jpa.services.interfaces.ISocioService;

@RestController
@RequestMapping("/rest/certificato")
public class CertificatoController {
	
	private ISocioService service;
	private ICertificatoService cService;

	public CertificatoController(ISocioService service, ICertificatoService cService) {
		this.service = service;
		this.cService = cService;
	}


	@GetMapping("/list")
	public List<SocioDTO> list() {
		ResponseList<SocioDTO> r = new ResponseList<SocioDTO>();
		try {
			r.setDati(service.listAll());
			r.setRC(true);
		} catch  (Exception e) {
			r.setRC(false);
			r.setMsg(e.getMessage());
		}
		return service.listAll();
	}
	
	@PostMapping("/create")
	public ResponseBase create(@RequestBody (required = true) CertificatoReq req) {
		ResponseBase r = new ResponseBase();
		try {
			cService.create(req);
			r.setRC(true);
		} catch (Exception e) {
			r.setRC(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}


}
