package com.formacionbdi.springboot.app.item;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
	
	@Bean("clienteRest")
	@LoadBalanced //Esta anotacion usara ribbon por defecto para balancear la carga
	public RestTemplate registrarRestTemplate() {
		return new RestTemplate();
	}

}
