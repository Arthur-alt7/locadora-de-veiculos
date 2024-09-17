package com.example.LocadoraDeVeiculos.models;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_CLIENTE")
public class ClienteModel extends RepresentationModel<ClienteModel> implements Serializable{
    private static final long serialVersionUID = 1l; //Numero de controle de versão de cada classe criada

    @Id
    @Column(name = "cpf")
    private String cpf;
    private String nome;
    private Double carteira;
    @OneToMany(mappedBy = "clienteLocacao", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<LocacaoModel> locacao;

    public ClienteModel(String nome, String cpf, double carteira) {
        this.nome = nome;
        this.cpf = cpf;
        this.carteira = carteira;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<LocacaoModel> getLocacao() {
        return locacao;
    }

    public void setLocacao(List<LocacaoModel> locacao) {
        this.locacao = locacao;
    }

    public Double getCarteira() {
        return carteira;
    }

    public void setCarteira(Double carteira) {
        this.carteira = carteira;
    }

}
