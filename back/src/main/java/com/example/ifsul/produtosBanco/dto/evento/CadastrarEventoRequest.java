package com.example.ifsul.produtosBanco.dto.evento;

import com.example.ifsul.produtosBanco.entities.Eventos;

import java.time.LocalDateTime;

public record CadastrarEventoRequest(
        String descricao,
        String descricaoTipo,
        String uri,
        String situacao

) {

    public CadastrarEventoRequest(Eventos evento){
        this(evento.getDescricao(), evento.getDescricaoTipo(), evento.getUri(), evento.getSituacao());
    }
}
