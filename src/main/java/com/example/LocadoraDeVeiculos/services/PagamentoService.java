package com.example.LocadoraDeVeiculos.services;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.LocadoraDeVeiculos.models.ClienteModel;
import com.example.LocadoraDeVeiculos.models.LocacaoModel;
import com.example.LocadoraDeVeiculos.models.PagamentoModel;
import com.example.LocadoraDeVeiculos.repositories.PagamentoRepository;

@Service
public class PagamentoService {     

    @Autowired
    private PagamentoRepository PagamentoRepository;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private LocacaoService locacaoService;

    public PagamentoModel createPagamento(PagamentoModel pagamento) throws Exception {
        ClienteModel cliente = clienteService.findClienteByCpf(pagamento.getClientePagamento().getCpf());
        LocacaoModel locacao = locacaoService.validarIdLocacao(pagamento.getLocacaoPagamento());

        clienteService.validarPagamento(cliente, pagamento.getValor());
        pagamento.setClientePagamento(cliente);
        pagamento.setLocacaoPagamento(locacao);   
        cliente.setCarteira(cliente.getCarteira()-pagamento.getValor());;
        locacao.setPagamento("Efetuado");

        this.clienteService.saveCliente(cliente);
        this.locacaoService.saveLocacao(locacao);
        
        return PagamentoRepository.save(pagamento);
    }

    public void savePagamento(PagamentoModel pagamentoModel){
        PagamentoRepository.save(pagamentoModel);
    }

    



}