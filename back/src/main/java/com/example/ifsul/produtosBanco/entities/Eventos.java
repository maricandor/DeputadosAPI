package com.example.ifsul.produtosBanco.entities;

import com.example.ifsul.produtosBanco.dto.evento.CadastrarEventoRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Eventos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uri;
    private String situacao;
    private String descricaoTipo;
    private String descricao;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "deputado_eventos",
            joinColumns = @JoinColumn(name = "deputado_id"),
            inverseJoinColumns = @JoinColumn(name = "eventos_id"))
    private List<Deputado> deputados;

    public Eventos(String descricao, LocalDateTime data, String cidade, String situacao, String uri) {
        this.descricao = descricao;
        this.descricaoTipo = cidade;
        this.situacao = situacao;
        this.uri = uri;
    }

    public Eventos(CadastrarEventoRequest dados){
        this.descricao = dados.descricao();
        this.situacao = dados.situacao();
        this.descricaoTipo = dados.descricaoTipo();
        this.uri = dados.uri();
    }


    public void vincularEvento(Deputado deputado) {
        this.deputados.add(deputado);
        }
    }

