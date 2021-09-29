package com.example.testlocal.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class SignupController {

    private final JavaMailSender javaMailSender;

    @PostMapping("/sendSejongEmail")
    public void sendSejongEmail(@RequestBody Map<String, String> map) throws MessagingException {

        String email = map.get("email") + "@sju.ac.kr";

        // db에서 이메일 중복 체크 해야함.

        // 키값 생성

        // 메일 보내기
        MimeMessage message = javaMailSender.createMimeMessage();
        message.setFrom("Sejong Coding Helper<sjhelper10@gmail.com>");
        message.setSubject("hi");
        message.setRecipient(Message.RecipientType.TO,new InternetAddress(email));
        message.setText("hi");
        message.setSentDate(new Date());
        javaMailSender.send(message);
    }

}
