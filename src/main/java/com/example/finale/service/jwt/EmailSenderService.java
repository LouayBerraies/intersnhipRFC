package com.example.finale.service.jwt;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {
    @Autowired
    private JavaMailSender mailSender;



    public void sendConfirmationEmail(String toEmail) throws MessagingException, jakarta.mail.MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);



        // Récupérer le contenu du template Thymeleaf
        String emailContent = "accepte yhdik";
        // Paramètres de l'e-mail
        helper.setFrom("maszounyasmine@gmail.com");
        helper.setTo(toEmail);
        helper.setSubject("Confirmation de paiement");
        helper.setText(emailContent, true);

        // Envoyer l'e-mail
        mailSender.send(message);
        System.out.println("Mail Send...");
    }

   /* public void sendCouponEmail(String email, Coupon coupon)  throws MessagingException, jakarta.mail.MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        // Construire le contexte pour le template Thymeleaf
        Context context = new Context();
        context.setVariable("coupon", coupon.getCode());

        // Récupérer le contenu du template Thymeleaf
        String emailContent = templateEngine.process("CodePromoEmail", context);

        // Paramètres de l'e-mail
        helper.setFrom("saadallahchaima58@gmail.com");
        helper.setTo(email);
        helper.setSubject("Code Promo");
        helper.setText(emailContent, true);

        // Envoyer l'e-mail
        mailSender.send(message);
        System.out.println("Mail Send...");
    }*/
}

