package com.example.LocadoraDeVeiculos.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.LocadoraDeVeiculos.dtos.ClienteDTO;
import com.example.LocadoraDeVeiculos.dtos.LocacaoDTO;
import com.example.LocadoraDeVeiculos.exception.ResourceNotFoundException;
import com.example.LocadoraDeVeiculos.models.ClienteModel;
import com.example.LocadoraDeVeiculos.models.LocacaoModel;
import com.example.LocadoraDeVeiculos.repositories.LocacaoRepository;
import com.example.LocadoraDeVeiculos.services.LocacaoService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import jakarta.validation.Valid;

@RestController
public class LocacaoController {

    @Autowired
    LocacaoRepository locacaoRepository;
    @Autowired
    LocacaoService locacaoService;

    @PostMapping("/locacao")
    public ResponseEntity<?> saveLocacao(@RequestBody @Valid LocacaoDTO locacaoDTO) throws Exception{
        try{
            var locacaoModel = new LocacaoModel();
            BeanUtils.copyProperties(locacaoDTO, locacaoModel);//Conversão de DTO para model
            locacaoService.createLocacao(locacaoModel);
            return ResponseEntity.status(HttpStatus.CREATED).body(locacaoModel);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }            
    }

    @GetMapping("/locacao")
    public ResponseEntity<List<LocacaoModel>> getAllLocacao() throws Exception{
        List<LocacaoModel> locacaoList = this.locacaoService.getAllLocacao();//usando o repositório novamente para buscar os dados do banco
        if(!locacaoList.isEmpty()){
            for(LocacaoModel locacao : locacaoList){//Correr a lista para inserir link
                UUID idLocacao = locacao.getIdLocacao();
                locacao.add(linkTo(methodOn(LocacaoController.class).getOneLocacao(idLocacao)).withSelfRel());//Criar link relacionado
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(locacaoList);
    }

    @GetMapping("/locacao/{idLocacao}")
    public ResponseEntity<Object> getOneLocacao(@PathVariable(value="idLocacao") UUID idLocacao) throws Exception{
        LocacaoModel locacao = this.locacaoService.findLocacaoById(idLocacao);
        locacao.add(linkTo(methodOn(LocacaoController.class).getAllLocacao()).withRel("Locacao list"));
        return ResponseEntity.status(HttpStatus.OK).body(locacao);
    }

    @PutMapping("/locacao/{idLocacao}")
    public ResponseEntity<Object> updateLocacao(@PathVariable(value="idLocacao") UUID idLocacao,
                                                @RequestBody @Valid LocacaoDTO locacaoDTO) throws Exception{
        try{
            LocacaoModel updateLocacao = locacaoService.updateLocacao(idLocacao, locacaoDTO);
            return ResponseEntity.status(HttpStatus.OK).body(updateLocacao);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }

    @DeleteMapping("/locacao/{idLocacao}")
    public ResponseEntity<Object> deleteLocacao(@PathVariable(value="idLocacao") UUID idLocacao) throws Exception{
        try {
            locacaoService.deleteLocacao(idLocacao);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Locacao not found.");
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.OK).body(e.getMessage());
        }
    }
    
    
    
    
}
