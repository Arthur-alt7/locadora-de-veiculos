package com.example.LocadoraDeVeiculos.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.LocadoraDeVeiculos.dtos.VeiculoDTO;
import com.example.LocadoraDeVeiculos.models.VeiculoModel;
import com.example.LocadoraDeVeiculos.repositories.VeiculoRepository;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import jakarta.validation.Valid;

@RestController
public class VeiculoController {
    
    @Autowired
    VeiculoRepository veiculoRepository;

    @PostMapping("/veiculos")
    public ResponseEntity<VeiculoModel> saveVeiculo(@RequestBody @Valid VeiculoDTO veiculoDTO){
        var veiculoModel = new VeiculoModel();
        BeanUtils.copyProperties(veiculoDTO, veiculoModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(veiculoRepository.save(veiculoModel));
    }

    @GetMapping("/veiculos")
    public ResponseEntity<List<VeiculoModel>> getAllVeiculos(){
        List<VeiculoModel> veiculoList   = veiculoRepository.findAll();
        if(!veiculoList.isEmpty()){
            for(VeiculoModel veiculo : veiculoList){
                String placa = veiculo.getPlaca();
                veiculo.add(linkTo(methodOn(VeiculoController.class).getOneVeiculo(placa)).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(veiculoList);
    }   

    @GetMapping("/veiculo/{placa}")
    public ResponseEntity<Object> getOneVeiculo(@PathVariable(value = "placa") String placa){
        Optional<VeiculoModel> veiculo0 = veiculoRepository.findById(placa);
         if (veiculo0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Veiculo not found");                                                                        
        }                                    
        veiculo0.get().add(linkTo(methodOn(VeiculoController.class).getAllVeiculos()).withRel("Veiculo list"));
        return ResponseEntity.status(HttpStatus.OK).body(veiculo0.get());
    }

    @PutMapping("/veiculo/{placa}")
    public ResponseEntity<Object> updateVeiculo(@PathVariable(value="placa") String placa,
                                                @RequestBody @Valid VeiculoDTO veiculoDTO){
        Optional<VeiculoModel> veiculo0 = veiculoRepository.findById(placa);
        if (veiculo0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Veiculo not found   !!!.");
        }
        var veiculoModel = veiculo0.get();
        BeanUtils.copyProperties(veiculoDTO, veiculoModel);
        return ResponseEntity.status(HttpStatus.OK).body(veiculoRepository.save(veiculoModel));
         }


    @DeleteMapping("/veiculo/{placa}")
    public ResponseEntity<Object> deleteVeiculo(@PathVariable(value="placa") String placa){
        Optional<VeiculoModel> veiculo0 = veiculoRepository.findById(placa);
        if (veiculo0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Veiculo not found.");
        }
        veiculoRepository.delete(veiculo0.get());
        return ResponseEntity.status(HttpStatus.OK).body("Veiculo deleted successfully");
         }



















}