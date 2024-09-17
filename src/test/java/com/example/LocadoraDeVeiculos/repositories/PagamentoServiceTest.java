package com.example.LocadoraDeVeiculos.repositories;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.LocadoraDeVeiculos.models.ClienteModel;
import com.example.LocadoraDeVeiculos.models.LocacaoModel;
import com.example.LocadoraDeVeiculos.services.ClienteService;
import com.example.LocadoraDeVeiculos.services.LocacaoService;
import com.example.LocadoraDeVeiculos.services.PagamentoService;

public class PagamentoServiceTest {
    
    @Mock
    private PagamentoRepository PagamentoRepository;
    @Mock
    private ClienteService clienteService;
    @Mock
    private LocacaoService locacaoService;

    @Autowired
    @InjectMocks
    private PagamentoService pagamentoService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Sucesso de pagamento")
    void createPagamento() {
         ClienteModel cliente = new ClienteModel("99999999999", "Arthur", 999.0);
         LocacaoModel locacao = new LocacaoModel(2, 5.0);

         when(clienteService.findClienteByCpf("99999999999")).thenReturn(cliente);
         when(locacaoService.validarIdLocacao(locacao))
    }


}
