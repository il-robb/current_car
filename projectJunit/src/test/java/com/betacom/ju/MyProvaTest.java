package com.betacom.ju;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MyProvaTest {
	
	@Test
	public void calcoloTest() {
		MyProva p = new MyProva();
		Assertions.assertThat(p.calcolo(2, 3)).isEqualTo(5); //assicurati che calcolo (2+3) = 5
		
	}
}
