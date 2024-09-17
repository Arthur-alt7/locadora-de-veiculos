package com.example.LocadoraDeVeiculos.repositories;

import com.example.LocadoraDeVeiculos.models.ClienteModel;
import com.example.LocadoraDeVeiculos.models.VeiculoModel;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VeiculoRepository extends JpaRepository <VeiculoModel, String>{
    Optional<VeiculoModel> findById(String placa);
}
