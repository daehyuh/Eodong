package com.example.eodong.controller;

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
    @Autowired
    public EmailController(EmailService emailService, MemberService memberService){
        this.emailService = emailService;
        this.memberService = memberService;
    }

    @GetMapping(value = "/api/email/findAll")
    @ResponseBody
    public List<Email> findAllEmail() {
        List<Email> members = emailService.findAll();
        return members;
    }

    @PostMapping(value = "/api/findPw")
    public String findPw(@ModelAttribute EmailForm form) throws Exception {
        System.out.println(form.emailMail);
        emailService.findPwControl(form.emailMail);
        return "home";
    }

    @PostMapping(value = "/api/email/join")
    public String joinEmail(@ModelAttribute EmailForm form) throws Exception {
        System.out.println(form.emailMail);
        emailService.sendPwControl(form.emailMail);
        return "home";
    }

    @PostMapping("/api/email/check")
    @ResponseBody
    public ResponseEntity check(@RequestParam String email, String pw) throws Exception {
        Boolean confirm = emailService.findTopByEmailMailAndEmailNumberOrderByEmailDate(email, pw);
        if (confirm){
            ResponseEntity responseEntity = new ResponseEntity();
            responseEntity.setCode(200);
            responseEntity.setMessage("인증됨");
            return responseEntity;
        } else {
            ResponseEntity responseEntity = new ResponseEntity();
            responseEntity.setCode(500);
            responseEntity.setMessage("인증 안됨");
            return responseEntity;
        }
    }

    static class ResponseEntity {
        private int code;
        private String message;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

}
