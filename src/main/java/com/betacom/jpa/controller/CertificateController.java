package com.betacom.jpa.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.jpa.dto.SocioDTO;
import com.betacom.jpa.requests.CertificatoReq;
import com.betacom.jpa.response.ResponseBase;
import com.betacom.jpa.response.ResponseList;
import com.betacom.jpa.services.interfaces.ICertificatoServices;

@RestController  //notazione per springboot, cercare cosa fa
@RequestMapping("/rest/certificato")
public class CertificateController {

	private ICertificatoServices CertS;
	
	public CertificateController(ICertificatoServices certS) {
		super();
		CertS = certS;
	}

	@GetMapping("/list")
	public List<SocioDTO> test() {
		ResponseList<SocioDTO> r = new ResponseList<SocioDTO>();
		try {
			r.setDati(CertS.lisAll());
		} catch (Exception e) {
			r.setRc(false);r.setMsg(e.getMessage());
		}
		return r.getDati();
	}
	
	@PostMapping("/create")
    public ResponseBase create(@RequestBody (required = true) CertificatoReq reqC) {
        ResponseBase r = new ResponseBase();
        try {
        	CertS.create(reqC);
            r.setRc(true);
        } catch (Exception e) {
            r.setRc(false);
            r.setMsg(e.getMessage());
        }

        return r;
    }
	
	@PutMapping("/update")
    public ResponseBase update(@RequestBody (required = true) CertificatoReq reqC) {
        ResponseBase r = new ResponseBase();
        try {
        	CertS.update(reqC);
            r.setRc(true);
        } catch (Exception e) {
            r.setRc(false);
            r.setMsg(e.getMessage());
        }

        return r;
    }
}