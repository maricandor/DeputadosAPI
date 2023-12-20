package com.example.ifsul.produtosBanco.dto.evento;

import com.example.ifsul.produtosBanco.entities.Eventos;

import java.time.LocalDateTime;

public record ListarEventosResponse(
        Long id,
        String descricao,
        String descricaoTipo,
        String situaçao
) {
    public ListarEventosResponse (Eventos eventos){
        this(eventos.getId(), eventos.getDescricao(), eventos.getDescricaoTipo(), eventos.getSituacao());
    }
}
