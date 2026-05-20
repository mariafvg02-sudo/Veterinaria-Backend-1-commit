package com.proyecto.veterinaria.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendRecoveryCode(String toEmail, String code) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(toEmail);
        message.setSubject("Recuperación de contraseña");
        message.setText(
                "Hola.\n\n" +
                "Tu código de recuperación es: " + code +
                "\n\nNo compartas este código."
        );

        mailSender.send(message);
    }
}