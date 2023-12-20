package com.example.ifsul.produtosBanco.controller;

import java.util.ArrayList;
import java.util.List;

import com.example.ifsul.produtosBanco.entities.Deputado;
import com.example.ifsul.produtosBanco.entities.Eventos;
import com.example.ifsul.produtosBanco.services.ApiService;
import com.example.ifsul.produtosBanco.services.DeputadoService;
import com.example.ifsul.produtosBanco.services.EventosService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

@RestController
public class ApiController {

	@Autowired
	private ApiService apiService; // Injeção de dependência para o serviço de API
	@Autowired
	private DeputadoService depService; // Injeção de dependência para o serviço de Deputado
	@Autowired
	private EventosService eventosService; // Injeção de dependência para o serviço de Eventos

	@GetMapping(value = "/data",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getDataDeputado()  {
		// Obtém os dados dos deputados do serviço de API
		String data = apiService.getDataDeputados();
		// Retorna os dados como uma resposta HTTP JSON
		return ResponseEntity.ok(data);
	}

	@GetMapping(value = "/dataEvento",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getDataEvento()  {
		// Obtém os dados dos eventos do serviço de API
		String data = apiService.getDataEventos();
		// Retorna os dados como uma resposta HTTP JSON
		return ResponseEntity.ok(data);
	}

	@GetMapping("/clone")
	public List<Deputado> clone() {
		// Obtém os dados dos deputados do serviço de API
		String data = apiService.getDataDeputados();
		// Converte os dados JSON em um objeto JSONObject
		JSONObject jsnobject = new JSONObject(data);
		// Obtém a lista de dados da chave "dados" no objeto JSON
		JSONArray jsonArray = jsnobject.getJSONArray("dados");
		// Lista temporária para armazenar objetos
		ArrayList<Object> listdata = new ArrayList<Object>();

		// Loop para clonar os primeiros 100 deputados
		for(int i = 0; i<100; i++) {
			// Converte cada objeto JSON em um objeto Deputado
			Deputado dep = new Gson().fromJson(jsonArray.get(i).toString(), Deputado.class);
			// Cria o deputado no banco de dados usando o serviço
			depService.criar(dep);
		}
		// Retorna a lista de deputados clonados
		return depService.listar();
	}

	@GetMapping("/cloneEventos")
	public List<Eventos> cloneEventos() {
		// Obtém os dados dos eventos do serviço de API
		String data = apiService.getDataEventos();
		// Converte os dados JSON em um objeto JSONObject
		JSONObject jsnobject = new JSONObject(data);
		// Obtém a lista de dados da chave "dados" no objeto JSON
		JSONArray jsonArray = jsnobject.getJSONArray("dados");

		// Loop para clonar eventos, evitando duplicatas
		for (int i = 0; i < jsonArray.length(); i++) {
			// Converte cada objeto JSON em um objeto Eventos
			Eventos evento = new Gson().fromJson(jsonArray.get(i).toString(), Eventos.class);

			// Verifica se o evento já foi clonado para evitar duplicatas
			if (!eventosService.eventoJaClonado(evento.getDescricao())) {
				// Cria o evento no banco de dados usando o serviço
				eventosService.criar(evento);
			}
		}
		// Retorna a lista de eventos clonados
		return eventosService.listar();
	}
}
