package com.example.ifsul.produtosBanco.dto.deputado;

import org.antlr.v4.runtime.misc.NotNull;

public record VincularEventoRequest(
        @NotNull
        Long idDeputado,
        @NotNull
        Long idEvento
) {
}
