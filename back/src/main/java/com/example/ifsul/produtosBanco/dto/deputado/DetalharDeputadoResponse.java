package com.example.ifsul.produtosBanco.dto.deputado;

import com.example.ifsul.produtosBanco.entities.Deputado;

public record DetalharDeputadoResponse(
        Long id,
        String nome,
        String siglaPartido,
        String siglaUf,
        Long idLegislatura,
        String urlFoto,
        String email

) {
    public DetalharDeputadoResponse(Deputado deputado){
        this(deputado.getId(),deputado.getNome(), deputado.getSiglaPartido(), deputado.getSiglaUf(), deputado.getIdLegislatura(), deputado.getUrlFoto(), deputado.getEmail());
    }
}
