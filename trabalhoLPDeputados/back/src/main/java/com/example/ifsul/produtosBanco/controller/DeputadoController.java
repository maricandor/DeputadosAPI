package com.example.ifsul.produtosBanco.controller;

import com.example.ifsul.produtosBanco.dto.deputado.VincularEventoRequest;
import com.example.ifsul.produtosBanco.dto.evento.ListarEventosResponse;
import com.example.ifsul.produtosBanco.dto.deputado.CadastraDeputadoRequest;
import com.example.ifsul.produtosBanco.dto.deputado.DetalharDeputadoResponse;
import com.example.ifsul.produtosBanco.dto.deputado.ListarDeputadosResponse;
import com.example.ifsul.produtosBanco.dto.ReturnResponse;
import com.example.ifsul.produtosBanco.services.DeputadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:8000", allowedHeaders = "*")
@RestController
@RequestMapping("/deputados")
public class DeputadoController {

    @Autowired
    private DeputadoService deputadoService;
/*
    @PostMapping("/cadastrar")
    @Transactional
    public ResponseEntity<ReturnResponse> cadastraDeputado(@RequestBody CadastraDeputadoRequest dados){
        var response = deputadoService.cadastraDeputado(dados);
        return ResponseEntity.status(response.status()).body(new ReturnResponse(response));
    }
 */

    @GetMapping
    public ResponseEntity<List<ListarDeputadosResponse>> listarDeputados(){
        var response = deputadoService.listarDeputados();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalharDeputadoResponse> detalharDeputado(@PathVariable Long id){
        var response = deputadoService.detalharDeputado(id);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/eventos/{id}")
    public ResponseEntity<List<ListarEventosResponse>> listarEventoPorDeputado(@PathVariable Long id){
        var response = deputadoService.listarEventos(id);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/vincular")
    public ResponseEntity<Map<String, String>> vincularEvento(@RequestBody VincularEventoRequest vincularReq) {
        if (vincularReq.idEvento() == null || vincularReq.idDeputado() == null) {
            return new ResponseEntity<>(Map.of("mensagem", "IDs de Evento ou Deputado não podem ser nulos"), HttpStatus.BAD_REQUEST);
        }
        deputadoService.vincularEvento(vincularReq.idEvento(), vincularReq.idDeputado());
        Map<String, String> mensagemSucesso = Map.of("mensagem", "Vínculo de evento realizado com sucesso.");
        return new ResponseEntity<>(mensagemSucesso, HttpStatus.OK);
    }
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<ReturnResponse> deletarDepu(@PathVariable Long id){
        var response = deputadoService.deletarDepu(id);
        return ResponseEntity.status(response.status()).body(new ReturnResponse(response));
    }

}
