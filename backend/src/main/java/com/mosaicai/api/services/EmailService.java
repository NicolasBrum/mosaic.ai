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
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(APIEMAIL));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(DESTINATION_EMAIL)
            );
            message.setSubject("🎉 Novo Usuário Cadastrado: " + personInfo.getName());

            String htmlContent = String.format("""
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
                            """, HEADEREMAIL,
                    personInfo.getName(),
                    personInfo.getSurname(),
                    personInfo.getRole(),
                    personInfo.getEnterpriseName(),
                    personInfo.getEmail(),
                    personInfo.getEmail());

            message.setContent(htmlContent, "text/html; charset=utf-8");

            Transport.send(message);
            System.out.println("E-mail enviado com sucesso!");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
