package com.betacom.jpa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.jpa.dto.AlimentazioneDTO;
import com.betacom.jpa.dto.VeicoloDTO;
import com.betacom.jpa.request.AlimentazioneReq;
import com.betacom.jpa.response.ResponseBase;
import com.betacom.jpa.response.ResponseList;
import com.betacom.jpa.services.interfaces.IAlimentazioneService;

@RestController
@RequestMapping("/rest/alimentazione")
public class AlimentazioneController {

	private IAlimentazioneService aliS;
	
	
	public AlimentazioneController(IAlimentazioneService aliS) {
		super();
		this.aliS = aliS;
	}

	@GetMapping("/listAll")
	public ResponseList<AlimentazioneDTO> listAll(){
		 ResponseList<AlimentazioneDTO> r = new  ResponseList<AlimentazioneDTO>();
		try {
			r.setDati(aliS.listAll());
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@PostMapping("/create")
	public ResponseBase create(@RequestBody (required = true)  AlimentazioneReq datiAlimentazione){
		 ResponseBase r = new  ResponseList<VeicoloDTO>();
		try {
			aliS.create(datiAlimentazione);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
}
