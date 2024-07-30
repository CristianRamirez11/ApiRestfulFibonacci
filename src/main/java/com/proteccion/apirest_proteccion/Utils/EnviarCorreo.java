package com.proteccion.apirest_proteccion.Utils;

import org.springframework.beans.factory.annotation.Value;

public class EnviarCorreo {
    @Value("${from.email.address}")
    private String fromEmailAddress;

    /*
    @Autowired
    private JavaMailSender mailSender;

    @Async
    public void sendEmail(String recipient, String subject, String content) throws UnsupportedEncodingException, MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom(fromEmailAddress, "My Email Address");
        helper.setTo(recipient);
        helper.setSubject(subject);
        helper.setText(content, true);
        mailSender.send(message);
    }
    */

}
