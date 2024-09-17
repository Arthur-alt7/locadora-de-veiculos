package com.example.LocadoraDeVeiculos.controllers;

import java.util.List;
import java.util.Optional;

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
import com.example.LocadoraDeVeiculos.models.ClienteModel;
import com.example.LocadoraDeVeiculos.repositories.ClienteRepository;
import com.example.LocadoraDeVeiculos.services.ClienteService;
import com.example.LocadoraDeVeiculos.exception.ResourceNotFoundException;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;


import jakarta.validation.Valid;

@RestController
public class ClienteController {
    
    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    ClienteService clienteService;

    @PostMapping("/clientes")
    public ResponseEntity<ClienteModel> saveCliente(@RequestBody @Valid ClienteDTO clienteDTO){
        var clienteModel = new ClienteModel();
        BeanUtils.copyProperties(clienteDTO, clienteModel);//Conversão de DTO para model
        clienteService.createCliente(clienteModel);//Chamar o service do cliente para salvar no banco.
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteModel);
    }

    @GetMapping("/clientes")
    public ResponseEntity<List<ClienteModel>> getAllClientes() throws Exception{
        List<ClienteModel> clienteList = this.clienteService.getAllCliente();//usando o repositório novamente para buscar os dados do banco
        if(!clienteList.isEmpty()){
            for(ClienteModel cliente : clienteList){//Correr a lista para inserir link
                String cpf = cliente.getCpf();
                cliente.add(linkTo(methodOn(ClienteController.class).getOneCliente(cpf)).withSelfRel());//Criar link relacionado
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(clienteList);
    }

    @GetMapping("/cliente/{cpf}")
    public ResponseEntity<Object> getOneCliente(@PathVariable(value="cpf") String cpf) throws Exception{
        ClienteModel cliente = this.clienteService.findClienteByCpf(cpf);
        cliente.add(linkTo(methodOn(ClienteController.class).getAllClientes()).withRel("Client list"));
        return ResponseEntity.status(HttpStatus.OK).body(cliente);
    }

    @PutMapping("/cliente/{cpf}")
    public ResponseEntity<Object> updateCliente(@PathVariable(value = "cpf") String cpf,
                                            @RequestBody @Valid ClienteDTO clienteDTO) throws Exception {
        try {
            ClienteModel updatedCliente = clienteService.updateCliente(cpf, clienteDTO);
            return ResponseEntity.status(HttpStatus.OK).body(updatedCliente);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/cliente/{cpf}")
    public ResponseEntity<Object> deleteCliente(@PathVariable(value="cpf") String cpf) throws Exception{ 
        try {
            clienteService.deleteCliente(cpf);
            return ResponseEntity.status(HttpStatus.OK).body("Cliente deletado com sucesso!");
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.OK).body(e.getMessage());
        }
    }






        




}
