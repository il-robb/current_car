package com.betacom.jpa.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.jpa.dto.AbbonamentoDTO;
import com.betacom.jpa.dto.RicevutaDTO;
import com.betacom.jpa.requests.AbbonamentoReq;
import com.betacom.jpa.response.ResponseBase;
import com.betacom.jpa.response.ResponseList;
import com.betacom.jpa.response.ResponseObject;
import com.betacom.jpa.services.interfaces.IAbbonamentoServices;

@RestController
@RequestMapping("/rest/abbonamento")
public class AbbonamentoController {

	private IAbbonamentoServices abboS;

	public AbbonamentoController(IAbbonamentoServices abboS) {
		this.abboS = abboS;
	}

	@PostMapping("/create")
	public ResponseBase create(@RequestBody (required = true)  AbbonamentoReq req) {
		ResponseBase r = new ResponseBase();
		try {
			abboS.create(req);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}

	@DeleteMapping("/delete")
	public ResponseBase delete(@RequestBody (required = true)  AbbonamentoReq req) {
		ResponseBase r = new ResponseBase();
		try {
			abboS.remove(req);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}

	
	@GetMapping("/getAbbonamento")
	public ResponseObject<AbbonamentoDTO> getAbbonamento(@RequestParam (required = true) Integer id){
		ResponseObject<AbbonamentoDTO> r = new ResponseObject<AbbonamentoDTO>();
		try {
			r.setDati(abboS.getById(id));
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@GetMapping("/getRicevuta")
	public ResponseObject<RicevutaDTO> getRicevuta(@RequestParam (required = true) Integer id){
		ResponseObject<RicevutaDTO> r = new ResponseObject<RicevutaDTO>();
		try {
			r.setDati(abboS.buildRicevuta(id));
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@GetMapping("/getAbbonamentoBySocio")
	public ResponseList<AbbonamentoDTO> getAbbonamentoBySocio(@RequestParam (required = true) Integer id){
		ResponseList<AbbonamentoDTO> r = new ResponseList<AbbonamentoDTO>();
		try {
			r.setDati(abboS.getAbbonamentiBySocio(id));
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
}
