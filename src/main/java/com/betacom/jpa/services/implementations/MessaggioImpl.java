package com.betacom.jpa.services.implementations;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.betacom.jpa.models.Messaggio;
import com.betacom.jpa.models.MessaggioId;
import com.betacom.jpa.repositories.IMessaggioRepository;
import com.betacom.jpa.services.interfaces.IMessaggioServices;

import lombok.extern.log4j.Log4j2;
@Log4j2
@Service
public class MessaggioImpl implements IMessaggioServices{
	
	private IMessaggioRepository msgR;
	
	public MessaggioImpl(IMessaggioRepository msgR) {
		this.msgR = msgR;
	}
	
	@Value("${lang}") //recupero lang da application.properties
	private String lang;
	
	@Override
	public String getMessaggio(String code) {
		log.debug("getMessaggio: " + code);
		String r = null;
		Optional<Messaggio> m = msgR.findById(new MessaggioId(lang, code));
		if (m.isEmpty()) {
			r = code;
		}
		else {
			r = m.get().getMessaggio();
		}
		
		return r;
	}

}
