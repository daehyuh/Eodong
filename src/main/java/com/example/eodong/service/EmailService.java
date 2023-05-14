package com.example.eodong.service;

import com.example.eodong.domain.Email;
import com.example.eodong.domain.MemberMajor;
import com.example.eodong.repository.EmailRepository;
import com.example.eodong.repository.MemberMajorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;


@Service
public class EmailService {
    private final EmailRepository emailRepository;

    @Autowired
    public EmailService(EmailRepository emailRepository){
        this.emailRepository = emailRepository;
    }

    public Long save(Email email){
        emailRepository.save(email);
        return email.getEmailIdx();
    }

    public List<Email> findAll(){
        return emailRepository.findAll();
    }

    public Boolean findTopByEmailMailAndEmailNumberOrderByEmailDate(String email, String pw){

        long diffInMillies = new Date().getTime() - emailRepository.findTopByEmailMailAndEmailNumberOrderByEmailDate(email, pw).get().getEmailDate().getTime();
        long diffInSeconds = TimeUnit.SECONDS.convert(diffInMillies, TimeUnit.MILLISECONDS);

        if (diffInSeconds > 300L){
            return false;
        } else {
            return true;
        }

    }

}
