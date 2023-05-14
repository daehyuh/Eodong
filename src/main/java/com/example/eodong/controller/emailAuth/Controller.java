package com.example.eodong.controller.emailAuth;


import com.example.eodong.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@org.springframework.stereotype.Controller
public class Controller {
    public final MemberService memberService;
    public final EmailService emailService;

    @Autowired
    public Controller(MemberService memberService, EmailService emailService) {
        this.memberService = memberService;
        this.emailService = emailService;
    }

    @PostMapping("/emailConfirm")
    public String emailConfirm(@RequestParam String email) throws Exception {

        String confirm = emailService.sendSimpleMessage(email);

        return confirm;
    }

    @PostMapping("/passwordCheck")
    @ResponseBody
    public String check(@RequestParam String password) throws Exception {

        Boolean confirm = emailService.checkPassword(password);
        if (confirm) {

            return "인증 되었습니다";
        } else {

            return "인증 되지 않았습니다";
        }

    }

}