package com.betacom.jpa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.jpa.dto.CategoriaDTO;
import com.betacom.jpa.dto.VeicoloDTO;
import com.betacom.jpa.request.CategoriaReq;
import com.betacom.jpa.response.ResponseBase;
import com.betacom.jpa.response.ResponseList;
import com.betacom.jpa.services.interfaces.IcategoriaService;

@RestController
@RequestMapping("/rest/categoria")
public class CategoriaController {

	private IcategoriaService cateS;

	public CategoriaController(IcategoriaService cateS) {
	super();
	this.cateS = cateS;
}

	@GetMapping("/listAll")
	public ResponseList<CategoriaDTO> listAll(){
		 ResponseList<CategoriaDTO> r = new  ResponseList<CategoriaDTO>();
		try {
			r.setDati(cateS.listAll());
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@PostMapping("/create")
	public ResponseBase create(@RequestBody (required = true)  CategoriaReq datiCategoria){
		 ResponseBase r = new  ResponseList<VeicoloDTO>();
		try {
			cateS.create(datiCategoria);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
}
