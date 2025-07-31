package com.betacom.jpa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.jpa.dto.TipoVeicoloDTO;
import com.betacom.jpa.dto.VeicoloDTO;
import com.betacom.jpa.request.AlimentazioneReq;
import com.betacom.jpa.request.CategoriaReq;
import com.betacom.jpa.request.TipoVeicoloReq;
import com.betacom.jpa.response.ResponseBase;
import com.betacom.jpa.response.ResponseList;
import com.betacom.jpa.services.interfaces.ITipoVeicoloService;

@RestController
@RequestMapping("/rest/tipoveicolo")
public class TipoVeicoloController {

	private ITipoVeicoloService tipoS;
	
	
	public TipoVeicoloController(ITipoVeicoloService tipoS) {
		super();
		this.tipoS = tipoS;
	}

	@PostMapping("/collegaAlimentazioneTipoVeicolo")
	public ResponseBase creatAlimentazioneTipoVeicolo(@RequestBody (required = true)  AlimentazioneReq aliReq){
		 ResponseBase r = new  ResponseList<VeicoloDTO>();
		try {
			tipoS.createAlimentazioneTipoVeicolo(aliReq);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	@PostMapping("/collegaCategoriaTipoVeicolo")
	public ResponseBase collegaCategoriaTipoVeicolo(@RequestBody (required = true)  CategoriaReq cateReq){
		 ResponseBase r = new  ResponseBase();
		try {
			tipoS.collegaCategoriaTipoVeicolo(cateReq);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@PostMapping("/createTipoVeicolo")
	public ResponseBase create(@RequestBody (required = true)  TipoVeicoloReq tipoReq){
		 ResponseBase r = new  ResponseBase();
		try {
			tipoS.create(tipoReq);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@GetMapping("/listall")
	public ResponseList<TipoVeicoloDTO> listall(){
		ResponseList<TipoVeicoloDTO> r = new  ResponseList<TipoVeicoloDTO>();
		try {
			r.setDati(tipoS.listall());
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	
}

