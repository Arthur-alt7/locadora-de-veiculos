package com.example.LocadoraDeVeiculos.dtos;

import java.util.UUID;

import com.example.LocadoraDeVeiculos.models.ClienteModel;
import com.example.LocadoraDeVeiculos.models.VeiculoModel;

import jakarta.validation.constraints.NotBlank;

public record LocacaoDTO (UUID idLocacao, int dias, VeiculoModel veiculoLocacao, ClienteModel clienteLocacao, Double valor, String pagamento){


}
