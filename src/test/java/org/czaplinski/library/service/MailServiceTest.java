package org.czaplinski.library.service;

import org.czaplinski.library.model.Mail;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MailServiceTest {
    @InjectMocks
    private MailService mailService;
    @Mock
    private JavaMailSender javaMailSender;

    @Test
    void ShouldSendEmail() {
        //Given
        Mail mail = new Mail("Test@test.com", "Test", "Test Message");

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getMailTo());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());

        //When
        javaMailSender.send(mailMessage);

        //Then
        verify(javaMailSender, times(1)).send(mailMessage);
    }
}