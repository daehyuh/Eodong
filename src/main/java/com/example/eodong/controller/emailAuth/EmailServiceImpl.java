package com.example.eodong.controller.emailAuth;

import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.example.eodong.domain.Email;
import com.example.eodong.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService{
    public final MemberService memberService;
    private PasswordEncoder passwordEncoder;
    public final com.example.eodong.service.EmailService emailService;

    @Autowired
    JavaMailSender emailSender;

    public static String ePw;

    public EmailServiceImpl(MemberService memberService, PasswordEncoder passwordEncoder, com.example.eodong.service.EmailService emailService1) {
        this.memberService = memberService;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService1;
    }

    private MimeMessage createMessage(String to)throws Exception{
        System.out.println("receiver : "+ to);
        System.out.println("password : "+ ePw);
        MimeMessage  message = emailSender.createMimeMessage();


        memberService.updatePw(passwordEncoder.encode(ePw), to);

        message.addRecipients(RecipientType.TO, to);//보내는 대상
        message.setSubject("임시 비밀번호 안내");//제목

        String msgg="";
        msgg+= "<div style='margin:20px;'>";
        msgg+= "<h1> 안녕하세요 외동입니다. </h1>";
        msgg+= "<br>";
        msgg+= "<p>아래 임시비밀번호로 로그인 해 주세요.<p>";
        msgg+= "<br>";
        msgg+= "<p>감사합니다.<p>";
        msgg+= "<br>";
        msgg+= "<div align='center' style='border:1px solid black; font-family:verdana';>";
        msgg+= "<h3 style='color:blue;'>임시 비밀번호입니다. 꼭 비밀번호를 변경해주세요.</h3>";
        msgg+= "<div style='font-size:130%'>";
        msgg+= "비밀번호 : <strong>";
        msgg+= ePw+"</strong><div><br/> ";
        msgg+= "</div>";
        message.setText(msgg, "utf-8", "html");//내용
        message.setFrom(new InternetAddress(to,"외동"));//보내는 사람

        return message;
    }


    private MimeMessage createMessage2(String to)throws Exception{
        System.out.println("receiver : "+ to);
        System.out.println("password : "+ ePw);
        MimeMessage  message = emailSender.createMimeMessage();
        message.addRecipients(RecipientType.TO, to);//보내는 대상
        message.setSubject("인증번호 안내");//제목

        String msgg="";
        msgg+= "<div style='margin:20px;'>";
        msgg+= "<h1> 안녕하세요 외동입니다. </h1>";
        msgg+= "<br>";
        msgg+= "<p> 인증번호 입니다.<p>";
        msgg+= "<br>";
        msgg+= "<p>감사합니다.<p>";
        msgg+= "<br>";
        msgg+= "<div align='center' style='border:1px solid black; font-family:verdana';>";
        msgg+= "<h3 style='color:blue;'>회원가입 인증 번호입니다</h3>";
        msgg+= "<div style='font-size:130%'>";
        msgg+= "인증번호 : <strong>";
        msgg+= ePw+"</strong><div><br/> ";
        msgg+= "</div>";
        message.setText(msgg, "utf-8", "html");//내용
        message.setFrom(new InternetAddress(to,"외동"));//보내는 사람

        Email email = new Email();
        email.setEmailDate(new Date());
        email.setEmailMail(to);
        email.setEmailNumber(ePw);
        emailService.save(email);

        return message;
    }


    public static String createKey() {
        StringBuffer key = new StringBuffer();
        Random rnd = new Random();

        for (int i = 0; i < 8; i++) { // 인증코드 8자리
            int index = rnd.nextInt(3); // 0~2 까지 랜덤

            switch (index) {
                case 0:
                    key.append((char) ((int) (rnd.nextInt(26)) + 97));
                    //  a~z  (ex. 1+97=98 => (char)98 = 'b')
                    break;
                case 1:
                    key.append((char) ((int) (rnd.nextInt(26)) + 65));
                    //  A~Z
                    break;
                case 2:
                    key.append((rnd.nextInt(10)));
                    // 0~9
                    break;
            }
        }
        return key.toString();
    }
    @Override
    public String sendSimpleMessage(String to)throws Exception {
        ePw = createKey();
        // TODO Auto-generated method stub
        MimeMessage message = createMessage(to);
        try{//예외처리
            emailSender.send(message);
        }catch(MailException es){
            es.printStackTrace();
            throw new IllegalArgumentException();
        }
        return ePw;
    }

    @Override
    public String sendSimpleMessage2(String to)throws Exception {
        ePw = createKey();
        // TODO Auto-generated method stub
        MimeMessage message = createMessage2(to);
        try{//예외처리
            emailSender.send(message);
        }catch(MailException es){
            es.printStackTrace();
            throw new IllegalArgumentException();
        }
        return ePw;
    }

    @Override
    public boolean checkPassword(String email, String password)  {
        if (emailService.findTopByEmailMailAndEmailNumberOrderByEmailDate(email, password)){
            return true;
        } else {
            return false;
        }
    }


}