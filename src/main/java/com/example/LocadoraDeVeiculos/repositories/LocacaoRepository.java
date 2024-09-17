package com.example.LocadoraDeVeiculos.repositories;

import com.example.LocadoraDeVeiculos.models.LocacaoModel;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocacaoRepository extends JpaRepository <LocacaoModel, UUID>{
    Optional<LocacaoModel> findById(UUID idLocacao);
}
