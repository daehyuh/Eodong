package com.example.eodong.controller.emailAuth;

import com.example.eodong.domain.Email;
import com.example.eodong.service.EmailService;
import com.example.eodong.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EmailController {

    public final EmailService emailService;
    public final MemberService memberService;
    public final com.example.eodong.controller.emailAuth.EmailService emailService2;
    @Autowired
    public EmailController(EmailService emailService, MemberService memberService, com.example.eodong.controller.emailAuth.EmailService emailService2){
        this.emailService = emailService;
        this.memberService = memberService;
        this.emailService2 = emailService2;
    }

    @GetMapping(value = "/api/email/findAll")
    @ResponseBody
    public List<Email> findAllEmail() {
        List<Email> members = emailService.findAll();
        return members;
    }

    @PostMapping(value = "/api/email/changePw")
    public String changePw(@ModelAttribute EmailForm form) throws Exception {
        System.out.println(form.emailMail);
        emailService2.sendSimpleMessage(form.emailMail);
        return "home";
    }

    @PostMapping(value = "/api/email/join")
    public String joinEmail(@ModelAttribute EmailForm form) throws Exception {
        System.out.println(form.emailMail);
        emailService2.sendSimpleMessage2(form.emailMail);
        return "home";
    }

    @PostMapping("/passwordCheck")
    @ResponseBody
    public String  check(@RequestParam String email, String pw) throws Exception {

        Boolean confirm = emailService2.checkPassword(email, pw);
        if (confirm) {
            return "인증 됨";
        } else {
            return "인증 되지 않았습니다";
        }

    }


}
