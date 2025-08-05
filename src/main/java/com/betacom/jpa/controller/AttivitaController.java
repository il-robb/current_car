package com.betacom.jpa.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.jpa.dto.AttivitaDTO;
import com.betacom.jpa.dto.SocioDTO;
import com.betacom.jpa.requests.AbbonamentoReq;
import com.betacom.jpa.requests.AttivitaReq;
import com.betacom.jpa.response.ResponseBase;
import com.betacom.jpa.response.ResponseList;
import com.betacom.jpa.services.interfaces.IAttivitaServices;

@RestController
@RequestMapping("/rest/attivita")
public class AttivitaController {

	private IAttivitaServices attivS;

	public AttivitaController(IAttivitaServices attivS) {
		this.attivS = attivS;
	}
	
	@PostMapping("create")
	public ResponseBase create(@RequestBody (required = true)  AttivitaReq req) {
		ResponseBase r = new ResponseBase();
		try {
			attivS.create(req);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@PutMapping("update")
	public ResponseBase update(@RequestBody (required = true)  AttivitaReq req) {
		ResponseBase r = new ResponseBase();
		try {
			attivS.update(req);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}

	@DeleteMapping("delete")
	public ResponseBase delete(@RequestBody (required = true)  AttivitaReq req) {
		ResponseBase r = new ResponseBase();
		try {
			attivS.delete(req);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}

	@PostMapping("createAttivitaAbbonamento")
	public ResponseBase createAttivitaAbbonamento(@RequestBody (required = true)  AttivitaReq req) {
		ResponseBase r = new ResponseBase();
		try {
			attivS.createAttivitaAbbonamento(req);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}

	@PostMapping("removeAttivitaAbbonamento")
	public ResponseBase removeAttivitaAbbonamento(@RequestBody (required = true)  AttivitaReq req) {
		ResponseBase r = new ResponseBase();
		try {
			attivS.removeAttivitaAbbonamento(req);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}

	
	@GetMapping("/list")
	public ResponseList<AttivitaDTO> list() {
		ResponseList<AttivitaDTO> r = new ResponseList<AttivitaDTO>();
		try {
			r.setDati(attivS.list());
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}

}
