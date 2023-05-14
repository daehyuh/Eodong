package com.example.eodong.controller;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class MemberForm {
    @NotBlank
    public String MEMBER_ID;
    @NotBlank
    public String MEMBER_PW;
    @NotBlank
    public String MEMBER_NAME;
    @NotBlank
    public String MEMBER_UNI_ID;
    @NotBlank
    public String MEMBER_PHONE;
    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형식에 맞지 않습니다.")
    public String MEMBER_EMAIL;
    @NotBlank
    public String MEMBER_MAJOR;

    public String getMEMBER_ID() {
        return MEMBER_ID;
    }

    public void setMEMBER_ID(String MEMBER_ID) {
        this.MEMBER_ID = MEMBER_ID;
    }

    public String getMEMBER_PW() {
        return MEMBER_PW;
    }

    public void setMEMBER_PW(String MEMBER_PW) {
        this.MEMBER_PW = MEMBER_PW;
    }

    public String getMEMBER_NAME() {
        return MEMBER_NAME;
    }

    public void setMEMBER_NAME(String MEMBER_NAME) {
        this.MEMBER_NAME = MEMBER_NAME;
    }

    public String getMEMBER_UNI_ID() {
        return MEMBER_UNI_ID;
    }

    public void setMEMBER_UNI_ID(String MEMBER_UNI_ID) {
        this.MEMBER_UNI_ID = MEMBER_UNI_ID;
    }

    public String getMEMBER_PHONE() {
        return MEMBER_PHONE;
    }

    public void setMEMBER_PHONE(String MEMBER_PHONE) {
        this.MEMBER_PHONE = MEMBER_PHONE;
    }

    public String getMEMBER_EMAIL() {
        return MEMBER_EMAIL;
    }

    public void setMEMBER_EMAIL(String MEMBER_EMAIL) {
        this.MEMBER_EMAIL = MEMBER_EMAIL;
    }

    public String getMEMBER_MAJOR() {
        return MEMBER_MAJOR;
    }

    public void setMEMBER_MAJOR(String MEMBER_MAJOR) {
        this.MEMBER_MAJOR = MEMBER_MAJOR;
    }

    @Override
    public String toString() {
        return "MemberForm{" +
                "MEMBER_ID='" + MEMBER_ID + '\'' +
                ", MEMBER_PW='" + MEMBER_PW + '\'' +
                ", MEMBER_NAME='" + MEMBER_NAME + '\'' +
                ", MEMBER_UNI_ID=" + MEMBER_UNI_ID +
                ", MEMBER_PHONE='" + MEMBER_PHONE + '\'' +
                ", MEMBER_EMAIL='" + MEMBER_EMAIL + '\'' +
                ", MEMBER_MAJOR='" + MEMBER_MAJOR + '\'' +
                '}';
    }
}
