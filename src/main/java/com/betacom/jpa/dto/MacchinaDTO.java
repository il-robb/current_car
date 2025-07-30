package com.betacom.jpa.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MacchinaDTO {
	    
		private Integer idMacchina;
		
		private Integer numeroPorte;
		
		private String targa;
		
		private Integer cilindrata;
		
		
}
