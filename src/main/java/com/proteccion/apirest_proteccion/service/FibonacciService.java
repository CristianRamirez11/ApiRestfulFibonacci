package com.proteccion.apirest_proteccion.service;

import com.proteccion.apirest_proteccion.model.FibonacciModel;
import com.proteccion.apirest_proteccion.repository.FibonacciRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class FibonacciService {

    @Autowired
    private FibonacciRepository fibonacciRepository;

    public FibonacciModel createFibonacciModel(FibonacciModel FibonacciModel){
        return fibonacciRepository.save(FibonacciModel);
    }

    public FibonacciModel getFibonacciModelById(Long id){
        Optional<FibonacciModel> FibonacciModelOptional = fibonacciRepository.findById(id);
        return FibonacciModelOptional.get();
    }

    public List<FibonacciModel> getAllFibonacciModels(){
        return fibonacciRepository.findAll();
    }

    public void deleteFibonacciModel(Long id){
        fibonacciRepository.deleteById(id);
    }

    public
}
