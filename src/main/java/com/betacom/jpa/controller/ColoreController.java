package com.betacom.jpa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.jpa.dto.ColoreDTO;
import com.betacom.jpa.request.ColoreReq;
import com.betacom.jpa.response.ResponseBase;
import com.betacom.jpa.response.ResponseList;
import com.betacom.jpa.services.interfaces.IColoreServices;

@RestController
@RequestMapping("/rest/colore")
public class ColoreController {

	private IColoreServices cS;

	public ColoreController(IColoreServices cS) {
		this.cS = cS;
	}
	
	@GetMapping("/listAll")
	public ResponseList<ColoreDTO> listAll(){
		ResponseList<ColoreDTO> r = new ResponseList<ColoreDTO>();
		try {
			r.setDati(cS.listAll());
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@PostMapping("/create")
	public ResponseBase create(@RequestBody (required = true)  ColoreReq datiColore) {
		ResponseBase r = new ResponseList<ColoreDTO>();
		try {
			cS.create(datiColore);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	
	
}
