package com.betacom.jpa.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MotoDTO {

	private Integer idMoto;
	
	private String targa;
		
	private Integer cilindrata;
	
}
