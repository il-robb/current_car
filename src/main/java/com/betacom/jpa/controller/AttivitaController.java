package com.betacom.jpa.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.jpa.dto.AttivitaDTO;
import com.betacom.jpa.requests.AttivitaReq;
import com.betacom.jpa.response.ResponseBase;
import com.betacom.jpa.response.ResponseList;
import com.betacom.jpa.services.interfaces.IAttivitaService;


@RestController  //notazione per springboot, cercare cosa fa
@RequestMapping("/rest/attivita")
public class AttivitaController {

	private IAttivitaService attS;
	
	
	public AttivitaController(IAttivitaService attS) {
		super();
		this.attS = attS;
		}


	@GetMapping("/list")
	public List<AttivitaDTO> test() {
		ResponseList<AttivitaDTO> r = new ResponseList<AttivitaDTO>();
		try {
			r.setDati(attS.listAll());
		} catch (Exception e) {
			r.setRc(false);r.setMsg(e.getMessage());
		}
		return r.getDati();
	}
	
	@PostMapping("/create")
    public ResponseBase create(@RequestBody (required = true) AttivitaReq reqC) {
        ResponseBase r = new ResponseBase();
        try {
        	attS.create(reqC);
            r.setRc(true);
        } catch (Exception e) {
            r.setRc(false);
            r.setMsg(e.getMessage());
        }

        return r;
    }
	
	@PostMapping("/createAttivitaAbbonamento")
    public ResponseBase attivitaAbbonamento(@RequestBody (required = true) AttivitaReq reqC) {
        ResponseBase r = new ResponseBase();
        try {
        	attS.creaAttivitaAbbonamento(reqC);
            r.setRc(true);
        } catch (Exception e) {
            r.setRc(false);
            r.setMsg(e.getMessage());
        }

        return r;
    }
	
	@PutMapping("/update")
    public ResponseBase update(@RequestBody (required = true) AttivitaReq reqC) {
        ResponseBase r = new ResponseBase();
        try {
        	attS.update(reqC);
            r.setRc(true);
        } catch (Exception e) {
            r.setRc(false);
            r.setMsg(e.getMessage());
        }

        return r;
    }
}
