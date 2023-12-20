package com.example.ifsul.produtosBanco.dto;

public record ReturnResponse(
        String mensagem
) {
    public ReturnResponse(MensagemResponse dados){
        this(dados.mensagem());
    }
}
