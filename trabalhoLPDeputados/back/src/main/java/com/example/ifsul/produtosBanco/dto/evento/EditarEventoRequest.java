package com.example.ifsul.produtosBanco.dto.evento;

import java.time.LocalDateTime;

public record EditarEventoRequest(
        String uri,
        String situacao,
        String descricaoTipo,
        String descricao
) {
}
