package com.betacom.jpa.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;


@Configuration
public class WebClientConfig {

	@Bean
	public WebClient jpawebClient(@Value("${jpa_backend}") String backendUrl) {
		
		return WebClient.builder()
				.baseUrl(backendUrl)
				.build();
	}
}
