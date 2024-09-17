package com.example.LocadoraDeVeiculos.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.LocadoraDeVeiculos.dtos.LocacaoDTO;
import com.example.LocadoraDeVeiculos.exception.EmptyClientListException;
import com.example.LocadoraDeVeiculos.models.LocacaoModel;
import com.example.LocadoraDeVeiculos.repositories.LocacaoRepository;

@Service
public class LocacaoService {

    @Autowired
    private LocacaoRepository locacaoRepository;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private VeiculoService veiculoService;


    public LocacaoModel createLocacao(LocacaoModel locacao) throws Exception {        
        
        //Associar o cliente existente a locacao
        locacao.setVeiculoLocacao(veiculoService.validarPlaca(locacao.getVeiculoLocacao()));
        locacao.setClienteLocacao(clienteService.validarCpf(locacao.getClienteLocacao()));
        locacao.setValor(valorLocacao(locacao));

        //Salvar a locacao
        return locacaoRepository.save(locacao);
    }

    public List<LocacaoModel> getAllLocacao(){
        List<LocacaoModel> locacao = this.locacaoRepository.findAll();
        if(locacao.isEmpty()){
            throw new EmptyClientListException("Locacoes não econtradas!");
        }
        return locacao;
    }

    public LocacaoModel updateLocacao(UUID idLocacao, LocacaoDTO locacaoDTO) throws Exception{
        LocacaoModel locacao = findLocacaoById(idLocacao);
        LocacaoModel locacaoAtt =  new LocacaoModel();
        BeanUtils.copyProperties(locacaoDTO, locacaoAtt);
        //Atualiza campos da entidade, mas não o ID 
        locacao.setDias(locacaoAtt.getDias());
        locacao.setStatus(locacaoAtt.getStatus());      
        
        locacao.setVeiculoLocacao(veiculoService.validarPlaca(locacaoAtt.getVeiculoLocacao()));
        locacao.setClienteLocacao(clienteService.validarCpf(locacaoAtt.getClienteLocacao()));
        

        return locacaoRepository.save(locacao);
    }

    public void deleteLocacao(UUID id) throws Exception{
        LocacaoModel locacaoModel = findLocacaoById(id);
        locacaoRepository.delete(locacaoModel);
    }

    public void saveLocacao(LocacaoModel locacaoModel){
        locacaoRepository.save(locacaoModel);
    }

    public LocacaoModel findLocacaoById(UUID id) throws Exception{
        return this.locacaoRepository.findById(id).orElseThrow(() -> new Exception("Locacao não encontrada"));
    }

    public LocacaoModel validarIdLocacao(LocacaoModel locacaoModel) throws Exception {
        var idlocacao = locacaoModel.getIdLocacao();
        LocacaoModel locacao = findLocacaoById(idlocacao);
        return (locacao);
    }

    public Double valorLocacao(LocacaoModel locacaoModel){
        double valor = locacaoModel.getDias()*46.7;

        return (valor);
    }





}
