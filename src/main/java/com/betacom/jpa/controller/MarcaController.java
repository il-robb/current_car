package com.betacom.jpa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.jpa.dto.MarcaDTO;
import com.betacom.jpa.request.MarcaReq;
import com.betacom.jpa.response.ResponseBase;
import com.betacom.jpa.response.ResponseList;
import com.betacom.jpa.services.interfaces.IMarcaService;


@RestController
@RequestMapping("/rest/marca")
public class MarcaController {

	private IMarcaService marS;

	public MarcaController(IMarcaService mS) {
		this.marS = mS;
	}
	
	@GetMapping("/listAll")
	public ResponseList<MarcaDTO> listAll(){
		ResponseList<MarcaDTO> r = new ResponseList<MarcaDTO>();
		try {
			r.setDati(marS.listAll());
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@PostMapping("/create")
	public ResponseBase create(@RequestBody (required = true)  MarcaReq datiMarca) {
		ResponseBase r = new ResponseList<MarcaDTO>();
		try {
			marS.create(datiMarca);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
}