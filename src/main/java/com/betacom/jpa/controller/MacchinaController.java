package com.betacom.jpa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.jpa.dto.VeicoloDTO;
import com.betacom.jpa.request.MacchinaReq;
import com.betacom.jpa.request.VeicoloReq;
import com.betacom.jpa.response.ResponseBase;
import com.betacom.jpa.response.ResponseList;
import com.betacom.jpa.services.interfaces.IMacchinaService;

@RestController
@RequestMapping("/rest/macchina")
public class MacchinaController {
	private IMacchinaService carS;

	public MacchinaController(IMacchinaService carS) {
		super();
		this.carS = carS;
	}
	
	@PostMapping("/create")
	public ResponseBase create(@RequestBody (required = true)  VeicoloReq datiVeicolo,@RequestBody (required = false)  MacchinaReq datiMacchina){
		 ResponseBase r = new  ResponseBase();
		try {
			carS.create(datiMacchina,datiVeicolo);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@GetMapping("/listAllCar")
	public ResponseList<VeicoloDTO> listAllcar(){
		ResponseList<VeicoloDTO> r = new  ResponseList<VeicoloDTO>();
		try {
			r.setDati(carS.listAllCar());
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
}
