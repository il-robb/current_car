package com.betacom.jpa.response;

import java.util.List;

import lombok.Data;

@Data
public class ResponseList<T> extends ResponseBase{
	
	private List<T> dati;
}
