package com.example.LocadoraDeVeiculos.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.LocadoraDeVeiculos.models.VeiculoModel;
import com.example.LocadoraDeVeiculos.repositories.VeiculoRepository;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;


    public VeiculoModel findVeiculoId(String placa) throws Exception{
        return veiculoRepository.findById(placa).orElseThrow(() -> new Exception("Veiculo não encontrado"));
    }

    public VeiculoModel validarPlaca(VeiculoModel veiculoModel) throws Exception{
        String placaV = veiculoModel.getPlaca();
        VeiculoModel veiculo = findVeiculoId(placaV);                
        return (veiculo);
    }
}
