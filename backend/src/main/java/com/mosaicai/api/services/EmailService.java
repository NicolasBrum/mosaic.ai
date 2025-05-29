package com.mosaicai.api.services;

import com.mosaicai.api.Constants.Constants;
import com.mosaicai.api.models.UserDtoTest;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.stereotype.Service;

import java.util.Properties;

import static com.mosaicai.api.Constants.Constants.*;

@Service
public class EmailService {
    public void enviarEmail(UserDtoTest personInfo) {
        final String username = Constants.APIEMAIL;
        final String password = Constants.APIEMAILPASKEY;

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(prop,
                new jakarta.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            // First email - New User Registration
            Message message1 = new MimeMessage(session);
            message1.setFrom(new InternetAddress(APIEMAIL));
            message1.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(DESTINATION_EMAIL)
            );
            message1.setSubject("🎉 Novo Usuário Cadastrado: " + personInfo.getName());

            String htmlContent1 = String.format("""
                            <html>
                            <body style="font-family: Arial, sans-serif; color: #333;">
                                <!-- Cabeçalho com imagem -->
                                <div style="text-align: center; padding: 10px;">
                                    <img src="%s" alt="Cabeçalho" style="width: 100%%; max-width: 600px;">
                                </div>

                                <!-- Corpo da mensagem -->
                                <div style="padding: 20px; background-color: #f9f9f9; border-radius: 5px; max-width: 600px; margin: auto;">
                                    <h2 style="color: #4CAF50;">🎉 Um novo usuário foi cadastrado!</h2>
                                    <p>Estamos felizes em informar que um novo usuário foi adicionado ao sistema. Confira os detalhes abaixo:</p>
                                    <ul style="list-style-type: none; padding: 0;">
                                        <li><strong>Nome:</strong> %s</li>
                                        <li><strong>Sobrenome:</strong> %s</li>
                                        <li><strong>Cargo:</strong> %s</li>
                                        <li><strong>Empresa:</strong> %s</li>
                                        <li><strong>Email:</strong> <a href="mailto:%s">%s</a></li>
                                    </ul>
                                    <p style="margin-top: 20px;">Se precisar de mais informações, é só entrar em contato. 😊</p>
                                </div>
                            </body>
                            </html>
                            """, HEADEREMAILPRODUCTOWNER,
                    personInfo.getName(),
                    personInfo.getSurname(),
                    personInfo.getRole(),
                    personInfo.getEnterpriseName(),
                    personInfo.getEmail(),
                    personInfo.getEmail());

            message1.setContent(htmlContent1, "text/html; charset=utf-8");

            // Second email - Agradecimento and Updates
            Message message2 = new MimeMessage(session);
            message2.setFrom(new InternetAddress(APIEMAIL));
            message2.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(personInfo.getEmail())
            );
            message2.setSubject("🎉 Atualização sobre a Plataforma Mosaic Ai!");

            String htmlContent2 = String.format("""
                            <html>
                            <body style="font-family: Arial, sans-serif; color: #333;">
                                <!-- Cabeçalho com imagem -->
                                <div style="text-align: center; padding: 10px;">
                                    <img src="%s" alt="Cabeçalho" style="width: 100%%; max-width: 600px;">
                                </div>

                                <!-- Corpo da mensagem -->
                                <div style="padding: 20px; background-color: #f9f9f9; border-radius: 5px; max-width: 600px; margin: auto;">
                                    <h2 style="color: #4CAF50;">Agradecemos seu interesse!</h2>
                                    <p>Olá %s %s,</p> 
                                    <p>Você será um dos primeiros a receber atualizações exclusivas da nossa plataforma. Estamos empolgados em tê-lo conosco!</p>
                                    <p style="margin-top: 20px;">Se precisar de mais informações, é só entrar em contato. 😊</p>
                                </div>
                            </body>
                            </html>
                            """, HEADEREMAILCLIENT, personInfo.getName(), personInfo.getSurname());

            message2.setContent(htmlContent2, "text/html; charset=utf-8");

            // Sending both emails
            Transport.send(message1);
            Transport.send(message2);

            System.out.println("E-mails enviados com sucesso!");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
