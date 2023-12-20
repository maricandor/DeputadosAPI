package com.example.ifsul.produtosBanco.services;

import com.example.ifsul.produtosBanco.dto.deputado.DetalharDeputadoResponse;
import com.example.ifsul.produtosBanco.dto.evento.CadastrarEventoRequest;
import com.example.ifsul.produtosBanco.dto.MensagemResponse;
import com.example.ifsul.produtosBanco.dto.ReturnResponse;
import com.example.ifsul.produtosBanco.dto.evento.EditarEventoRequest;
import com.example.ifsul.produtosBanco.dto.evento.ListarEventosResponse;
import com.example.ifsul.produtosBanco.entities.Eventos;
import com.example.ifsul.produtosBanco.repositories.DeputadoRepo;
import com.example.ifsul.produtosBanco.repositories.EventosRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventosService {

    @Autowired
    private EventosRepo eventosRepository;

    @Autowired
    private DeputadoRepo deputadoRepository;

    // Cria um novo evento no banco de dados
    public Eventos criar(Eventos evento) {
        return eventosRepository.save(evento);
    }

    // Retorna a lista de todos os eventos
    public List<Eventos> listar() {
        return eventosRepository.findAll();
    }

    // Retorna os detalhes de um evento específico no formato de resposta DTO
    public ListarEventosResponse detalharEvento(Long id) {
        var evento = eventosRepository.getReferenceById(id);
        return new ListarEventosResponse(evento);
    }

    // Retorna a lista de eventos no formato de resposta DTO
    public List<ListarEventosResponse> listarEventos() {
        var list = eventosRepository.findAll();
        return list.stream()
                .map(ListarEventosResponse::new)
                .collect(Collectors.toList());
    }

    // Edita as informações de um evento
    public MensagemResponse editarEvento(Long id, EditarEventoRequest dados){
        var evento = eventosRepository.getReferenceById(id);
        boolean mudou = false;

        // Verifica se cada campo foi alterado e atualiza o evento
        if (dados.uri() != null && !dados.uri().equals(evento.getUri())) {
            evento.setUri(dados.uri());
            mudou = true;
        }

        if (dados.situacao() != null && !dados.situacao().equals(evento.getSituacao())) {
            evento.setSituacao(dados.situacao());
            mudou = true;
        }

        if (dados.descricaoTipo() != null && !dados.descricaoTipo().equals(evento.getDescricaoTipo())) {
            evento.setDescricaoTipo(dados.descricaoTipo());
            mudou = true;
        }

        if (dados.descricao() != null && !dados.descricao().equals(evento.getDescricao())) {
            evento.setDescricao(dados.descricao());
            mudou = true;
        }

        // Salva as alterações no banco de dados
        eventosRepository.save(evento);
        // Retorna a mensagem de sucesso ou informa que nenhuma informação foi alterada
        if(mudou){
            return new MensagemResponse(200, "Evento editado com sucesso!");
        }
        return new MensagemResponse(200, "Nenhuma informação foi alterada");
    }

    // Deleta um evento do banco de dados
    public MensagemResponse deletarEvento(Long id){
        var evento = eventosRepository.getReferenceById(id);
        eventosRepository.delete(evento);

        return new MensagemResponse(202, "Evento deletado com sucesso!");
    }

    // Verifica se um evento com a descrição fornecida já existe no banco de dados
    public boolean eventoJaClonado(String descricao) {
        return eventosRepository.existsByDescricao(descricao);
    }
}
