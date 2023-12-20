package com.example.ifsul.produtosBanco.repositories;

import com.example.ifsul.produtosBanco.entities.Deputado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DeputadoRepo extends JpaRepository<Deputado, Long>{
    Optional<Deputado> findById(Long idDeputado);
}
