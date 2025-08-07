package com.betacom.fe.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
	
	@Bean
	WebClient socioWebClient(@Value("${jpa.backend}") String backendUrl){
		return WebClient.builder()
				.baseUrl(backendUrl)
				.build();
	}
}
