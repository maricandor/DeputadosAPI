package com.example.ifsul.produtosBanco.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	public String getDataDeputados() {
		String url = "https://dadosabertos.camara.leg.br/api/v2/deputados";
		ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
		String responseBody = responseEntity.getBody();
		
		return responseBody;
		
	}
	public String getDataEventos() {
		String url = "https://dadosabertos.camara.leg.br/api/v2/eventos";
//		String url = "https://dadosabertos.camara.leg.br/api/v2/eventos?ordem=ASC&ordenarPor=dataHoraInicio";
		ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
		String responseBody = responseEntity.getBody();

		return responseBody;
	}

}
