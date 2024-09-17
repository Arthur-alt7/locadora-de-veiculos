package com.example.LocadoraDeVeiculos.controllers;

import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.LocadoraDeVeiculos.dtos.PagamentoDTO;
import com.example.LocadoraDeVeiculos.models.PagamentoModel;
import com.example.LocadoraDeVeiculos.services.PagamentoService;

import jakarta.validation.Valid;

@RestController
public class PagamentoController {
    
    @Autowired
    PagamentoService pagamentoService;

    @PostMapping("/pagamento")
    public ResponseEntity<?> savePagamento(@RequestBody @Valid PagamentoDTO pagamentoDTO) throws Exception{
        try{
            var pagamentoModel =  new PagamentoModel();
            BeanUtils.copyProperties(pagamentoDTO, pagamentoModel);
            pagamentoService.createPagamento(pagamentoModel);
            return ResponseEntity.status(HttpStatus.CREATED).body(pagamentoModel);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
