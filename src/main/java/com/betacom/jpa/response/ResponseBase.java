package com.betacom.jpa.response;

import lombok.Data;

@Data
public class ResponseBase {
	private boolean rc; //response controller
	private String msg;
}
