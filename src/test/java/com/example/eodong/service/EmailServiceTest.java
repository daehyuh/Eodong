package com.example.eodong.service;

import com.example.eodong.domain.Email;
import com.example.eodong.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
class EmailServiceTest {
    public final EmailService emailService;
    @Autowired
    EmailServiceTest(EmailService emailService) {
        this.emailService = emailService;
    }

    @Test
    void 누가코드를영어로짜() {
        Email email = new Email();
        email.setEmailNumber("111");
        email.setEmailMail("rkdeown10@naver.com");
        email.setEmailDate(new Date());
        emailService.save(email);

    }
}