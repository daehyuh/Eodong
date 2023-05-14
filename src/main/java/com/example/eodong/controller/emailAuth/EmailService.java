package com.example.eodong.controller.emailAuth;

public interface EmailService {
    String sendSimpleMessage(String to)throws Exception;
    boolean checkPassword(String password)throws Exception;
}