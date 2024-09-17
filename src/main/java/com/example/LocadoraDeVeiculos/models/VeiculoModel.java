package com.example.LocadoraDeVeiculos.models;

import java.io.Serializable;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Table;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;



@Entity //Banco
@Table(name = "TB_VEICULO")
public class VeiculoModel extends RepresentationModel implements Serializable{
    private static final long serialVersionUID = 1l;
    
    @Id
    private String placa;
    private String modelo;
    private String cor;
    
    @OneToMany(mappedBy = "veiculoLocacao", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<LocacaoModel> locacao;

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }
 
    public List<LocacaoModel> getLocacao() {
        return locacao;
    }
 
    public void setLocacao(List<LocacaoModel> locacao) {
        this.locacao = locacao;
    }

}