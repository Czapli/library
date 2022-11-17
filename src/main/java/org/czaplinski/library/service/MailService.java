package org.czaplinski.library.service;

import lombok.RequiredArgsConstructor;
import org.czaplinski.library.model.Mail;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {
    private final JavaMailSender javaMailSender;
    private final LogService logService;

    public void send(final Mail mail) {
        try {
//            javaMailSender.send(createMail(mail));  //uncomment if set on heroku
            System.out.println("### sending mail ###");
        } catch (MailException e) {
            logService.saveLog("Failed to process email sending: " + e.getMessage());
        }
    }
    private SimpleMailMessage createMail(final Mail mail){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("servicelibrary@czaplinski.org");
        mailMessage.setTo(mail.getMailTo());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());
        return mailMessage;
    }
}
