package com.example.ifsul.produtosBanco.controller;

import com.example.ifsul.produtosBanco.dto.deputado.DetalharDeputadoResponse;
import com.example.ifsul.produtosBanco.dto.evento.CadastrarEventoRequest;
import com.example.ifsul.produtosBanco.dto.ReturnResponse;
import com.example.ifsul.produtosBanco.dto.evento.EditarEventoRequest;
import com.example.ifsul.produtosBanco.dto.evento.ListarEventosResponse;
import com.example.ifsul.produtosBanco.services.EventosService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8000", allowedHeaders = "*")
@RestController
@RequestMapping("/eventos")
public class EventosController {

    @Autowired
    private EventosService service;

    // Endpoint para detalhar informações de um evento específico
    @GetMapping("/{id}")
    public ResponseEntity<ListarEventosResponse> detalharEvento(@PathVariable Long id){
        // Chama o serviço para obter os detalhes do evento com o ID fornecido
        var response = service.detalharEvento(id);
        // Retorna os detalhes do evento como resposta HTTP
        return ResponseEntity.ok().body(response);
    }

    // Endpoint para listar todos os eventos
    @GetMapping
    public ResponseEntity<List<ListarEventosResponse>> listarEventos(){
        // Chama o serviço para obter a lista de todos os eventos
        var response = service.listarEventos();
        // Retorna a lista de eventos como resposta HTTP
        return ResponseEntity.ok().body(response);
    }

    // Endpoint para editar as informações de um evento
    @PutMapping("/editar/{id}")
    public ResponseEntity<ReturnResponse> editarEvento(@PathVariable Long id, @RequestBody EditarEventoRequest dados){
        // Chama o serviço para editar as informações do evento com o ID fornecido
        var response = service.editarEvento(id, dados);
        // Retorna a resposta do serviço como resposta HTTP
        return ResponseEntity.status(response.status()).body(new ReturnResponse(response));
    }

    // Endpoint para deletar um evento
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<ReturnResponse> deletarEvento(@PathVariable Long id){
        // Chama o serviço para deletar o evento com o ID fornecido
        var response = service.deletarEvento(id);
        // Retorna a resposta do serviço como resposta HTTP
        return ResponseEntity.status(response.status()).body(new ReturnResponse(response));
    }
}
