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
/*
    @PostMapping("/cadastrar")
    public ResponseEntity<ReturnResponse> cadastraEvento(@RequestBody CadastrarEventoRequest dados){
        var response = service.cadastraEvento(dados);
        return ResponseEntity.status(response.status()).body(new ReturnResponse(response));
    }
 */

    @GetMapping("/{id}")
    public ResponseEntity<ListarEventosResponse> detalharEvento(@PathVariable Long id){
        var response = service.detalharEvento(id);
        return ResponseEntity.ok().body(response);
    }
    @GetMapping
    public ResponseEntity<List<ListarEventosResponse>> listarEventos(){
        var response = service.listarEventos();
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<ReturnResponse> editarEvento(@PathVariable Long id, @RequestBody EditarEventoRequest dados){
        var response = service.editarEvento(id, dados);
        return ResponseEntity.status(response.status()).body(new ReturnResponse(response));
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<ReturnResponse> deletarEvento(@PathVariable Long id){
        var response = service.deletarEvento(id);
        return ResponseEntity.status(response.status()).body(new ReturnResponse(response));
    }
}
