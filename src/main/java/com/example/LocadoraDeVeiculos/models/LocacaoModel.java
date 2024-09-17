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

@Entity //Banco
@Table(name = "TB_LOCACAO")
public class LocacaoModel extends RepresentationModel implements Serializable{
    private static final long serialVersionUID = 1l; //Numero de controle de versão de cada classe criada

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_locacao")
    private UUID idLocacao;
    private int dias;
    private boolean status;
    private Double valor;
    private String pagamento; 

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "placa", referencedColumnName = "placa")
    //@JsonIgnore
    private VeiculoModel veiculoLocacao;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "cpf", referencedColumnName = "cpf")
    //@JsonIgnore
    private ClienteModel clienteLocacao;

    public LocacaoModel(int dias, Double valor){
        this.dias = dias;
        this.valor = valor;
    }


    public UUID getIdLocacao() {
        return idLocacao;
    }

    public void setIdLocacao(UUID idLocacao) {
        if(this.idLocacao == null){
            this.idLocacao = idLocacao;
        } else if (!this.idLocacao.equals(idLocacao)){
            throw new UnsupportedOperationException("No permission to change the ID of an existing entity");
        }
    }

    public VeiculoModel getVeiculoLocacao() {
        return veiculoLocacao;
    }

    public void setVeiculoLocacao(VeiculoModel veiculoLocacao) {
        this.veiculoLocacao = veiculoLocacao;
    }

    public ClienteModel getClienteLocacao() {
        return clienteLocacao;
    }

    public void setClienteLocacao(ClienteModel clienteLocacao) {
        this.clienteLocacao = clienteLocacao;
    }

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getPagamento() {
        return pagamento;
    }

    public void setPagamento(String pagamento) {
        this.pagamento = pagamento;
    }

}
