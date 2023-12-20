package com.example.ifsul.produtosBanco.dto.deputado;

public record CadastraDeputadoRequest(
        String nome,
        String siglaPartido,
        String siglaUf,
        Long idLegislatura,
  //      String urlFoto,
        String email
) {
}
