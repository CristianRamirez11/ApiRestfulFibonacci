package com.proteccion.apirest_proteccion.controller;

import com.proteccion.apirest_proteccion.model.FibonacciModel;
import com.proteccion.apirest_proteccion.service.FibonacciService;
import com.proteccion.apirest_proteccion.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/fibonacci")
public class FibonacciController {
    @Autowired
    private FibonacciService fibonacciService;

    @PostMapping
    public FibonacciModel createFibonacciModel(@RequestBody FibonacciModel FibonacciModel){
        return fibonacciService.createFibonacciModel(FibonacciModel);
    }

    @GetMapping
    public List<FibonacciModel> getAllFibonacciModel(){
        return fibonacciService.getAllFibonacciModels();
    }

    @GetMapping("{id}")
    public FibonacciModel searchFibonacciModelById(@PathVariable("id") Long id){
        return fibonacciService.getFibonacciModelById(id);
    }

    @DeleteMapping("{id}")
    public void deleteFibonacciModelById(@PathVariable("id") Long id){
        fibonacciService.deleteFibonacciModel(id);
    }

    @GetMapping("/count/{numero}")
    public long[] getFibonacciSeries(@PathVariable("numero") int count) {
        if (count < 2) {
            throw new IllegalArgumentException("El numero ingresado debe ser mayor o igual a 2");
        }
        return fibonacciService.calcularSerieFibonacci(count);
    }



}
