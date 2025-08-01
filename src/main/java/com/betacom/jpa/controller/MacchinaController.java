package com.betacom.jpa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.jpa.dto.VeicoloDTO;
import com.betacom.jpa.request.MacchinaVeicoloReq;
import com.betacom.jpa.response.ResponseBase;
import com.betacom.jpa.response.ResponseList;
import com.betacom.jpa.services.interfaces.IMacchinaService;
import com.betacom.jpa.services.interfaces.IVeicoloServices;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/rest/macchina")
public class MacchinaController {
	private IMacchinaService carS;
	private IVeicoloServices veiS;
	
	
	
	public MacchinaController(IMacchinaService carS, IVeicoloServices veiS) {
		super();
		this.carS = carS;
		this.veiS = veiS;
	}

	@PostMapping("/create")//,@RequestBody (required = false) MacchinaReq datiMacchina
	public ResponseBase create(@RequestBody (required = true)  MacchinaVeicoloReq datiVeicolo){
		ResponseBase r = new  ResponseBase();
		try {
			carS.create(datiVeicolo.getDatiMacchina(),datiVeicolo.getDatiVeicolo());
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
