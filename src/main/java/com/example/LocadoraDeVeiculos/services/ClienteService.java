package com.example.LocadoraDeVeiculos.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.LocadoraDeVeiculos.dtos.ClienteDTO;
import com.example.LocadoraDeVeiculos.exception.EmptyClientListException;
import com.example.LocadoraDeVeiculos.models.ClienteModel;
import com.example.LocadoraDeVeiculos.models.PagamentoModel;
import com.example.LocadoraDeVeiculos.repositories.ClienteRepository;

@Service
public class ClienteService {
    
    @Autowired
    private ClienteRepository clienteRepository;

    public ClienteModel createCliente(ClienteModel cliente){
        ClienteModel novoCliente = cliente;
        return clienteRepository.save(novoCliente);
    }

    public List<ClienteModel> getAllCliente() throws Exception{
        List<ClienteModel> clientes = this.clienteRepository.findAll();
        if (clientes.isEmpty()) {
            throw new EmptyClientListException("Clientes não encontrados!");
        }
        return clientes;  
    }

    public ClienteModel updateCliente(String cpf, ClienteDTO clienteDTO) throws Exception {
        ClienteModel clienteModel = findClienteByCpf(cpf);
        BeanUtils.copyProperties(clienteDTO, clienteModel);
        return clienteRepository.save(clienteModel);
    }

    public void deleteCliente(String cpf) throws Exception {
        ClienteModel clienteModel = findClienteByCpf(cpf);
        clienteRepository.delete(clienteModel);
    }

    public void validarPagamento(ClienteModel cliente, Double valor) throws Exception{
        if (cliente.getCarteira().compareTo(valor) < 0){
            throw new Exception("Saldo na carteira insuficiente");
        }
    }

    public ClienteModel findClienteByCpf(String cpf) throws Exception{
        return this.clienteRepository.findById(cpf).orElseThrow(() -> new Exception("Cliente não encontrado"));
    }

    public ClienteModel validarCpf(ClienteModel clienteModel) throws Exception {
        var Cpfv = clienteModel.getCpf();
        ClienteModel cliente = findClienteByCpf(Cpfv);       
        return (cliente);
    }

    public void saveCliente(ClienteModel clienteModel){
        clienteRepository.save(clienteModel);
    }


}
