package com.example.ifsul.produtosBanco.repositories;

import com.example.ifsul.produtosBanco.entities.Eventos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EventosRepo extends JpaRepository<Eventos, Long> {
    boolean existsByDescricao(String descricao);
    Optional<Eventos> findById(Long idEvento);
}
