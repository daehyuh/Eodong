package com.example.eodong.service;

import com.example.eodong.domain.Email;
import com.example.eodong.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;


@Service
public class EmailService {
    private final EmailRepository emailRepository;
    public final MemberService memberService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    JavaMailSender emailSender;

    public static String ePw;

    public EmailService(EmailRepository emailRepository, MemberService memberService, PasswordEncoder passwordEncoder){
        this.emailRepository = emailRepository;
        this.memberService = memberService;
        this.passwordEncoder = passwordEncoder;
    }

    public Long save(Email email){
        emailRepository.save(email);
        return email.getEmailIdx();
    }

    public List<Email> findAll(){
        return emailRepository.findAll();
    }

    public Boolean findTopByEmailMailAndEmailNumberOrderByEmailDate(String email, String pw){
        if (emailRepository.findTopByEmailMailAndEmailNumberOrderByEmailDate(email, pw).isEmpty()){
            return false;
        }

        long diffInMillies = new Date().getTime() - emailRepository.findTopByEmailMailAndEmailNumberOrderByEmailDate(email, pw).get().getEmailDate().getTime();
        long diffInSeconds = TimeUnit.SECONDS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        System.out.println(diffInSeconds);
        if (diffInSeconds > 300L) {
            return false;
        } else {
            return true;
        }

    }




    private MimeMessage findPw(String to)throws Exception{
        System.out.println("receiver : "+ to);
        System.out.println("password : "+ ePw);
        MimeMessage  message = emailSender.createMimeMessage();


        memberService.updatePw(passwordEncoder.encode(ePw), to);

        message.addRecipients(Message.RecipientType.TO, to);//보내는 대상
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


    private MimeMessage sendPw(String to)throws Exception{
        System.out.println("receiver : "+ to);
        System.out.println("password : "+ ePw);
        MimeMessage  message = emailSender.createMimeMessage();
        message.addRecipients(Message.RecipientType.TO, to);//보내는 대상
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
        emailRepository.save(email);

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

    public String findPwControl(String to)throws Exception {
        ePw = createKey();
        // TODO Auto-generated method stub
        MimeMessage message = findPw(to);
        try{//예외처리
            emailSender.send(message);
        }catch(MailException es){
            es.printStackTrace();
            throw new IllegalArgumentException();
        }
        return ePw;
    }


    public String sendPwControl(String to)throws Exception {
        ePw = createKey();
        // TODO Auto-generated method stub
        MimeMessage message = sendPw(to);
        try{//예외처리
            emailSender.send(message);
        }catch(MailException es){
            es.printStackTrace();
            throw new IllegalArgumentException();
        }
        return ePw;
    }

}
