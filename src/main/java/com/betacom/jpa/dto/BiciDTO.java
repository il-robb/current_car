package com.betacom.jpa.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BiciDTO {

			private Integer idBici;
			
			private Integer numeroMarce;
			
			private Boolean pieghevole;
			
			private String sospensione;
			
}
