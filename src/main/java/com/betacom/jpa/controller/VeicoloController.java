package com.betacom.jpa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.jpa.dto.VeicoloDTO;
import com.betacom.jpa.response.ResponseList;
import com.betacom.jpa.services.interfaces.IVeicoloServices;

@RestController
@RequestMapping("/rest/veicolo")
public class VeicoloController {

	private IVeicoloServices veiS;
	
	@GetMapping("/listAll")
	public ResponseList<VeicoloDTO> listAll(){
		 ResponseList<VeicoloDTO> r = new  ResponseList<VeicoloDTO>();
		try {
			r.setDati(veiS.listAll());
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	
}
