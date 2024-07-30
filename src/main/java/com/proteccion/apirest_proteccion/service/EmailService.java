package com.proteccion.apirest_proteccion.service;

import com.proteccion.apirest_proteccion.Utils.SolicitudEnvioCorreo;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


import java.io.UnsupportedEncodingException;

@Service
public class EmailService {

    @Value("${from.email.address}")
    private String fromEmailAddress;

    @Autowired
    private JavaMailSender mailSender;

    @Async
    public void sendEmail(SolicitudEnvioCorreo solicitudEnvioCorreo) throws UnsupportedEncodingException, MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom(fromEmailAddress, "Prueba Técnica – Cristian David Ramirez");
        helper.setTo(solicitudEnvioCorreo.getToEmail());
        helper.setSubject(solicitudEnvioCorreo.getAsuntoCorreo());
        helper.setText(solicitudEnvioCorreo.getCuerpoCorreo(), true);
        mailSender.send(message);
    }

}
