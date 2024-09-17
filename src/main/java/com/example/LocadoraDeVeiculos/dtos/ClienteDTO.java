package com.example.LocadoraDeVeiculos.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;

public record ClienteDTO (@NotBlank String cpf, @NotBlank String nome, Double carteira){
    
}
