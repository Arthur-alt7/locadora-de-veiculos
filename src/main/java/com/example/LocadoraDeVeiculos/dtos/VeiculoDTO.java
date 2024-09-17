package com.example.LocadoraDeVeiculos.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;

public record VeiculoDTO (@NotBlank String placa, @NotBlank String modelo, @NotBlank String cor){
    
    

}
