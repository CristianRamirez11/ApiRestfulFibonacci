package com.proteccion.apirest_proteccion.controller;

import com.proteccion.apirest_proteccion.Utils.FibonacciResponse;
import com.proteccion.apirest_proteccion.model.FibonacciModel;
import com.proteccion.apirest_proteccion.service.FibonacciService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("api/fibonacci")
@Api(value = "Controlador de FibonacciController", description = "Operaciones para el calulo de la serie de Fibonacci")
public class FibonacciController {

    @Autowired
    private FibonacciService fibonacciService;

    @PostMapping
    @ApiOperation(value = "Metodo que me permite agregar una serie segun el modelo FibonacciModel", response = String.class)
    public FibonacciModel createFibonacciModel(@RequestBody FibonacciModel FibonacciModel){
        return fibonacciService.createFibonacciModel(FibonacciModel);
    }

    @GetMapping
    @ApiOperation(value = "Metodo que me permite obtener todas las series calculdas", response = String.class)
    public List<FibonacciModel> getAllFibonacciModel(){
        return fibonacciService.getAllFibonacciModels();
    }

    @GetMapping("{id}")
    @ApiOperation(value = "Metodo que me permite obtener una serie por el Id", response = String.class)
    public FibonacciModel searchFibonacciModelById(@PathVariable("id") Long id){
        return fibonacciService.getFibonacciModelById(id);
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "Metodo que me permite eliminar una serie de Bd por el Id", response = String.class)
    public void deleteFibonacciModelById(@PathVariable("id") Long id){
        fibonacciService.deleteFibonacciModel(id);
    }

    @GetMapping("/count/{numero}")
    @ApiOperation(value = "Calcula la serie segun un numero dado", response = String.class)
    public long[] getFibonacciSeries(@PathVariable("numero") int count) throws MessagingException, UnsupportedEncodingException {
        if (count < 2) {
            throw new IllegalArgumentException("El numero ingresado debe ser mayor o igual a 2");
        }
        return fibonacciService.calcularSerieFibonacci(count);
    }

    @PostMapping("/date")
    @ApiOperation(value = "La construcción de la serie se basará en la hora exacta del día en que se \n" +
            "produce, los minutos de la hora serán la semilla de la serie, así como los \n" +
            "segundos serán la cantidad de números que se deben mostrar", response = String.class)
    public FibonacciResponse getFibonacciSeries(
            @RequestBody @DateTimeFormat(pattern = "HH:mm:ss") LocalTime executionTime) throws MessagingException, UnsupportedEncodingException {

        if(executionTime == null){
            throw new IllegalArgumentException("La fecha ingresa esta incorrecta, por favor validar.");
        }

        return fibonacciService.calcularSerieFibonacciWithMinutosAndSegundos(executionTime);
    }

}
