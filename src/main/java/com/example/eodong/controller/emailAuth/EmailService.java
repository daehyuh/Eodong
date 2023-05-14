package com.example.eodong.controller.emailAuth;

public interface EmailService {
    String sendSimpleMessage(String to)throws Exception;
    String sendSimpleMessage2(String to)throws Exception;
    boolean checkPassword(String email, String password)throws Exception;
}