package com.betacom.jpa.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.jpa.dto.SocioDTO;
import com.betacom.jpa.requests.SocioReq;
import com.betacom.jpa.response.ResponseBase;
import com.betacom.jpa.response.ResponseList;
import com.betacom.jpa.services.interfaces.ISocioService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/rest/socio")
public class SocioController {
	
	private ISocioService socioService;
	
	public SocioController(ISocioService socioService) {
		this.socioService = socioService;
	}

	@GetMapping("/test")
	public String test() {
		return "andiamo al whitemoon stase?";
	};
	
	@GetMapping("/list")
	public List<SocioDTO> list() {
		ResponseList<SocioDTO> r = new ResponseList<SocioDTO>();
		try {
			r.setDati(socioService.listAll());
			r.setRC(true);
		} catch  (Exception e) {
			r.setRC(false);
			r.setMsg(e.getMessage());
		}
		return socioService.listAll();
	}
	
	@PostMapping("/create")
	public ResponseBase create(@RequestBody (required = true) SocioReq req) {
		ResponseBase r = new ResponseBase();
		try {
			socioService.insert(req);
			r.setRC(true);
		} catch (Exception e) {
			r.setRC(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@DeleteMapping("delete")
	public ResponseBase delete(@RequestBody (required = true) SocioReq req) {
		ResponseBase r = new ResponseBase();
		try {
			socioService.delete(req);
			r.setRC(true);
		} catch (Exception e) {
			r.setRC(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}

	
	
	
}
