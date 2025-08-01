package com.betacom.jpa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.jpa.dto.SospensioneDTO;
import com.betacom.jpa.request.SospensioneReq;
import com.betacom.jpa.response.ResponseBase;
import com.betacom.jpa.response.ResponseList;
import com.betacom.jpa.services.interfaces.ISospensioneService;

@RestController
@RequestMapping("/rest/sospensione")
public class SospensioneController {

	private ISospensioneService sospS;

	public SospensioneController(ISospensioneService sS) {
		this.sospS = sS;
	}
	
	@GetMapping("/listAll")
	public ResponseList<SospensioneDTO> listAll(){
		ResponseList<SospensioneDTO> r = new ResponseList<SospensioneDTO>();
		try {
			r.setDati(sospS.listAll());
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@PostMapping("/create")
	public ResponseBase create(@RequestBody (required = true)  SospensioneReq datiSospensione) {
		ResponseBase r = new ResponseList<SospensioneDTO>();
		try {
			sospS.create(datiSospensione);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
}