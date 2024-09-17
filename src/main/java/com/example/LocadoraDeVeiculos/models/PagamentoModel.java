package com.example.LocadoraDeVeiculos.models;

import java.io.Serializable;
import java.util.UUID;

import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_PAGAMENTOS")
public class PagamentoModel extends RepresentationModel implements Serializable{
    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_pagamento")
    private UUID idPagamento;
    private Double valor;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "cpf", referencedColumnName = "cpf")
    private ClienteModel clientePagamento;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_locacao", referencedColumnName = "id_locacao")
    private LocacaoModel locacaoPagamento;


    
    public UUID getIdPagamento() {
        return idPagamento;
    }

    public void setIdPagamento(UUID idPagamento) {
        if(this.idPagamento == null){
            this.idPagamento = idPagamento;
        } else if (!this.idPagamento.equals(idPagamento)){
            throw new UnsupportedOperationException("No permission to change the ID of an existing entity");
        }

    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public ClienteModel getClientePagamento() {
        return clientePagamento;
    }

    public void setClientePagamento(ClienteModel clientePagamento) {
        this.clientePagamento = clientePagamento;
    }

    public LocacaoModel getLocacaoPagamento() {
        return locacaoPagamento;
    }

    public void setLocacaoPagamento(LocacaoModel locacaoPagamento) {
        this.locacaoPagamento = locacaoPagamento;
    }

}
