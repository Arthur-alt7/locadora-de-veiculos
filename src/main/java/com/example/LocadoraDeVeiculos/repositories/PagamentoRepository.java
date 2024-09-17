package com.example.LocadoraDeVeiculos.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.LocadoraDeVeiculos.models.PagamentoModel;

public interface PagamentoRepository extends JpaRepository <PagamentoModel, UUID>{
    Optional<PagamentoModel> findById(UUID idPagamento);
}
