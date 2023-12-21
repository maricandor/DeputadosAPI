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

    // Endpoint para listar todos os deputados
    @GetMapping
    public ResponseEntity<List<ListarDeputadosResponse>> listarDeputados(){
        // Chama o serviço para obter a lista de deputados
        var response = deputadoService.listarDeputados();
        // Retorna a lista de deputados como resposta HTTP
        return ResponseEntity.ok().body(response);
    }

    // Endpoint para detalhar informações de um deputado específico
    @GetMapping("/{id}")
    public ResponseEntity<DetalharDeputadoResponse> detalharDeputado(@PathVariable Long id){
        // Chama o serviço para obter os detalhes do deputado com o ID fornecido
        var response = deputadoService.detalharDeputado(id);
        // Retorna os detalhes do deputado como resposta HTTP
        return ResponseEntity.ok().body(response);
    }

    // Endpoint para listar eventos associados a um deputado específico
    @GetMapping("/eventos/{id}")
    public ResponseEntity<List<ListarEventosResponse>> listarEventoPorDeputado(@PathVariable Long id){
        // Chama o serviço para obter a lista de eventos associados ao deputado com o ID fornecido
        var response = deputadoService.listarEventos(id);
        // Retorna a lista de eventos como resposta HTTP
        return ResponseEntity.ok().body(response);
    }

    // Endpoint para vincular um evento a um deputado
    @PostMapping("/vincular")
    public ResponseEntity<Map<String, String>> vincularEvento(@RequestBody VincularEventoRequest vincularReq) {
        // Verifica se os IDs de Evento ou Deputado são nulos e retorna um erro 400 em caso afirmativo
        if (vincularReq.idEvento() == null || vincularReq.idDeputado() == null) {
            return new ResponseEntity<>(Map.of("mensagem", "IDs de Evento ou Deputado não podem ser nulos"), HttpStatus.BAD_REQUEST);
        }
        // Chama o serviço para vincular o evento ao deputado
        deputadoService.vincularEvento(vincularReq.idDeputado(), vincularReq.idEvento());
        // Retorna uma mensagem de sucesso como resposta HTTP
        Map<String, String> mensagemSucesso = Map.of("mensagem", "Vínculo de evento realizado com sucesso.");
        return new ResponseEntity<>(mensagemSucesso, HttpStatus.OK);
    }

    // Endpoint para deletar um deputado
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<ReturnResponse> deletarDepu(@PathVariable Long id){
        // Chama o serviço para deletar o deputado com o ID fornecido
        var response = deputadoService.deletarDepu(id);
        // Retorna a resposta do serviço como resposta HTTP
        return ResponseEntity.status(response.status()).body(new ReturnResponse(response));
    }
}
