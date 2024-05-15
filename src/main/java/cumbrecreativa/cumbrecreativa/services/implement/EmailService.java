package cumbrecreativa.cumbrecreativa.services.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;
    public void sendVerificationMail(String to, String code) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("miquel.marco@outlook.com");
        message.setTo(to);
        message.setSubject("Verifica tu Cuenta");
        message.setText("Por favor verifica tu cuenta haciendo click en el siguiente enlace " + "http://localhost:8080/api/verifyMail/" + code);
        mailSender.send(message);
    }
    public void passwordRecovery(String to, String token) {
        String resetLink = "http://localhost:8080/api/resetPassword/" + token;
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("miquel.marco@outlook.com");
        message.setTo(to);
        message.setSubject("Restablece tu contraseña");
        message.setText("Haz click para restablecer tu contraseña " + resetLink + " Código válido por una hora");
        mailSender.send(message);
    }
    public void sendVerificationAssistance(String to, String code) {
        String assistanceLink = "http://localhost:8080/api/confirmAssistance/" + code;
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("miquel.marco@outlook.com");
        message.setTo(to);
        message.setSubject("Confirma tu asistencia");
        message.setText("Confirma tu asistencia al evento haciendo click: " + assistanceLink);
        mailSender.send(message);
    }
}