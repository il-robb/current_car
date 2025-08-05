package com.betacom.jpa.services.implementations;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.betacom.jpa.models.MessageId;
import com.betacom.jpa.models.Messaggi;
import com.betacom.jpa.repositories.IMessageRepository;
import com.betacom.jpa.services.interfaces.IMessaggioServices;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class MessaggioImpl implements IMessaggioServices{
	private IMessageRepository msgR;

	public MessaggioImpl(IMessageRepository msgR) {
		this.msgR = msgR;
	}

	@Value("${lang}")
	private String lang;
	
	@Override
	public String getMessaggio(String code) {
		log.debug("getMessaggio :" + code);
		String r = null;
		Optional<Messaggi> m = msgR.findById(new MessageId(lang, code));
		if (m.isEmpty())
			r = code;
		else 
			r = m.get().getMessaggio();
		
		return r;
	}



}
