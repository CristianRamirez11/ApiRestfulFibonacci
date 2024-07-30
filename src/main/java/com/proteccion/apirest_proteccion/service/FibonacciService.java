package com.proteccion.apirest_proteccion.service;

import com.proteccion.apirest_proteccion.Utils.FibonacciResponse;
import com.proteccion.apirest_proteccion.Utils.SolicitudEnvioCorreo;
import com.proteccion.apirest_proteccion.model.FibonacciModel;
import com.proteccion.apirest_proteccion.repository.FibonacciRepository;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Arrays;
import java.util.stream.Collectors;

@Component
public class FibonacciService {

    @Autowired
    private FibonacciRepository fibonacciRepository;

    @Autowired
    private EmailService emailService;

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

    public long[] calcularSerieFibonacci(int count) throws MessagingException, UnsupportedEncodingException {
        long[] fibonacciSeries = new long[count];
        fibonacciSeries[0] = 0;
        fibonacciSeries[1] = 1;

        for (int i = 2; i < count; i++) {
            fibonacciSeries[i] = fibonacciSeries[i - 1] + fibonacciSeries[i - 2];
        }

        String arrayAsString = Arrays.toString(fibonacciSeries);
        LocalTime horaActual = LocalTime.now();

        //Se crea el objeto FibonacciModel para almacenar en BD
        FibonacciModel fibonacciModelNew = new FibonacciModel();
        fibonacciModelNew.setHoraGeneracion("Hora actual: " + horaActual);
        fibonacciModelNew.setSerieCalcula(arrayAsString);
        fibonacciModelNew.setMensajePersonalizado("Cálculo de la serie según un número dado por el usuario");

        //Se almacena la información en BD
        fibonacciRepository.save(fibonacciModelNew);

        //Se crea objeto para enviar al service, para el envío  del email
        SolicitudEnvioCorreo solicitudEnvioCorreo = new SolicitudEnvioCorreo();
        solicitudEnvioCorreo.setAsuntoCorreo("Prueba Técnica – Cristian David Ramirez");
        solicitudEnvioCorreo.setCuerpoCorreo("Envío de correo electrónico para validar la prueba técnica de Protección, " +
                                             fibonacciModelNew.getMensajePersonalizado() + " " +
                                             fibonacciModelNew.getHoraGeneracion() + " " +
                                             fibonacciModelNew.getSerieCalcula());
        solicitudEnvioCorreo.setToEmail("cdrz01@gmail.com");

        //emailService.sendEmail(solicitudEnvioCorreo);


        return fibonacciSeries;
    }

    public FibonacciResponse calcularSerieFibonacciWithMinutosAndSegundos(LocalTime horaIngresada) throws MessagingException, UnsupportedEncodingException {
        // Obtener los minutos
        int semillaSerieMinutos = horaIngresada.getMinute();
        // Obtener los segundos
        int count = horaIngresada.getSecond();

        //Separar los dígitos de los minutos para el calculo de las semillas
        int semilla1 = semillaSerieMinutos / 10;
        int semilla2 = semillaSerieMinutos % 10;

        List<Long> calculoSerieFibonacci = new ArrayList<>();
        calculoSerieFibonacci.add((long) semilla1);
        calculoSerieFibonacci.add((long) semilla2);

        for (int i = 2; i < count + 2; i++) {
            long nextNumber = calculoSerieFibonacci.get(i - 1) + calculoSerieFibonacci.get(i - 2);
            calculoSerieFibonacci.add(nextNumber);
        }

        // Crear la serie en orden descendente
        List<Long> fibonacciSeriesDesc = calculoSerieFibonacci.subList(2, calculoSerieFibonacci.size());
        fibonacciSeriesDesc.sort((a, b) -> Long.compare(b, a));

        //Se convierte la lista de tipo Long a String para ser almacenado en BD
        String listAsString = fibonacciSeriesDesc.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", ", "[", "]"));

        //Se crea el objeto FibonacciModel para almacenar en BD
        FibonacciModel fibonacciModelNew = new FibonacciModel();
        fibonacciModelNew.setHoraGeneracion("Hora ingresada por el Usuario: " + horaIngresada.toString());
        fibonacciModelNew.setSerieCalcula(listAsString);
        fibonacciModelNew.setMensajePersonalizado("Cálculo de la serie según una hora dada por el usuario");

        //Se almacena la información en BD
        fibonacciRepository.save(fibonacciModelNew);

        //Se crea objeto para enviar al service, para el envío  del email
        SolicitudEnvioCorreo solicitudEnvioCorreo = new SolicitudEnvioCorreo();
        solicitudEnvioCorreo.setAsuntoCorreo("Prueba Técnica – Cristian David Ramirez");
        solicitudEnvioCorreo.setCuerpoCorreo("Envío de correo electrónico para validar la prueba técnica de Protección, " +
                fibonacciModelNew.getMensajePersonalizado() + " " +
                fibonacciModelNew.getHoraGeneracion() + " " +
                fibonacciModelNew.getSerieCalcula());
        solicitudEnvioCorreo.setToEmail("cdrz01@gmail.com");

        //emailService.sendEmail(solicitudEnvioCorreo);

        return new FibonacciResponse(horaIngresada, fibonacciSeriesDesc);
    }
}
