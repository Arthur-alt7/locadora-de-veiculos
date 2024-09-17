package com.example.LocadoraDeVeiculos.repositories;

import com.example.LocadoraDeVeiculos.models.ClienteModel;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository  extends JpaRepository <ClienteModel, String>{
    Optional<ClienteModel> findById(String cpf);
}
