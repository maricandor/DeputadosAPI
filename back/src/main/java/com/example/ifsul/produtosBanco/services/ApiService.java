package com.example.ifsul.produtosBanco.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiService {

	@Autowired
	private RestTemplate restTemplate;

	// Método para obter dados dos deputados de uma API externa
	public String getDataDeputados() {
		String url = "https://dadosabertos.camara.leg.br/api/v2/deputados";
		// Faz uma requisição GET à API de deputados e obtém a resposta como uma string
		ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
		// Obtém o corpo da resposta
		String responseBody = responseEntity.getBody();

		return responseBody;
	}

	// Método para obter dados dos eventos de uma API externa
	public String getDataEventos() {
		String url = "https://dadosabertos.camara.leg.br/api/v2/eventos";
//		String url = "https://dadosabertos.camara.leg.br/api/v2/eventos?ordem=ASC&ordenarPor=dataHoraInicio";
		// Faz uma requisição GET à API de eventos e obtém a resposta como uma string
		ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
		// Obtém o corpo da resposta
		String responseBody = responseEntity.getBody();

		return responseBody;
	}

}
