package com.betacom.jpa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.jpa.dto.TipoVeicoloDTO;
import com.betacom.jpa.dto.VeicoloDTO;
import com.betacom.jpa.request.TipoVeicoloReq;
import com.betacom.jpa.response.ResponseBase;
import com.betacom.jpa.response.ResponseList;
import com.betacom.jpa.services.interfaces.ITipoVeicoloServices;

@RestController
@RequestMapping("/rest/tipoveicolo")
public class TipoVeicoloController {

	private ITipoVeicoloServices tvS;
	
	
	
	public TipoVeicoloController(ITipoVeicoloServices tvS) {
		this.tvS = tvS;
	}

	@GetMapping("/listAll")
	public ResponseList<TipoVeicoloDTO> listAll(){
		 ResponseList<TipoVeicoloDTO> r = new  ResponseList<TipoVeicoloDTO>();
		try {
			r.setDati(tvS.listAll());
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@PostMapping("/create")
	public ResponseBase create(@RequestBody (required = true)  TipoVeicoloReq datiTipoVeicolo){
		 ResponseBase r = new  ResponseList<VeicoloDTO>();
		try {
			tvS.create(datiTipoVeicolo);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@PostMapping("/createTipoVeicoloAlimentazione")
	public ResponseBase createTipoVeicolo(@RequestBody (required = true) TipoVeicoloReq datiTipoVeicolo) {
		ResponseBase r = new ResponseBase();
		try {
			tvS.createTipoVeicoloAlimentazione(datiTipoVeicolo);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		
		return r;
	}
}
