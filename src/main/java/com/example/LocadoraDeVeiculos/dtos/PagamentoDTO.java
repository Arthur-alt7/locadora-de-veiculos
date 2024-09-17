package com.example.LocadoraDeVeiculos.dtos;

import java.util.UUID;

import com.example.LocadoraDeVeiculos.models.ClienteModel;
import com.example.LocadoraDeVeiculos.models.LocacaoModel;

public record PagamentoDTO(UUID idPagamento, Double valor, ClienteModel clientePagamento, LocacaoModel locacaoPagamento) {

    
}
