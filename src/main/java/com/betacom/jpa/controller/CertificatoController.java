package com.betacom.jpa.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.jpa.dto.SocioDTO;
import com.betacom.jpa.requests.CertificatoReq;
import com.betacom.jpa.response.ResponseBase;
import com.betacom.jpa.response.ResponseList;
import com.betacom.jpa.services.interfaces.ICertificatoServices;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/rest/certificato")
public class CertificatoController {
	
	private ICertificatoServices certS;
	
	public CertificatoController(ICertificatoServices certS) {
		this.certS = certS;
	}
	
	@GetMapping("/list")
	public ResponseList<SocioDTO> list() {
		ResponseList<SocioDTO> r = new ResponseList<SocioDTO>();
		try {
			r.setDati(certS.listAll());
			r.setRc(true);
			
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		
		return r;
	}
	
	@PostMapping("create")
	public ResponseBase create(@RequestBody (required = true) CertificatoReq reqC) {
		ResponseBase r = new ResponseBase();
		try {
			certS.create(reqC);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@DeleteMapping("delete")
	public ResponseBase delete(@RequestBody (required = true) CertificatoReq req) {
		ResponseBase r = new ResponseBase();
		try {
			certS.create(req);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@PutMapping("update")
	public ResponseBase update(@RequestBody (required = true) CertificatoReq req) {
		ResponseBase r = new ResponseBase();
		try {
			certS.update(req);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
}
