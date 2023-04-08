package com.example.eodong.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Member {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "MEMBER_SEQ")
    @SequenceGenerator(name="MEMBER_SEQ", sequenceName = "MEMBER_SEQ", initialValue = 0, allocationSize = 1)
    public Long member_idx;
    public String member_major;
    public String member_id;
    public String member_pw;
    public String member_name;
    public int member_uni_id;
    public int member_phone;
    public String member_email;
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    public Date member_date;

    public Long getMember_idx() {
        return member_idx;
    }

    public void setMember_idx(Long member_idx) {
        this.member_idx = member_idx;
    }

    public String getMember_major() {
        return member_major;
    }

    public void setMember_major(String member_major) {
        this.member_major = member_major;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getMember_pw() {
        return member_pw;
    }

    public void setMember_pw(String member_pw) {
        this.member_pw = member_pw;
    }

    public String getMember_name() {
        return member_name;
    }

    public void setMember_name(String member_name) {
        this.member_name = member_name;
    }

    public int getMember_uni_id() {
        return member_uni_id;
    }

    public void setMember_uni_id(int member_uni_id) {
        this.member_uni_id = member_uni_id;
    }

    public int getMember_phone() {
        return member_phone;
    }

    public void setMember_phone(int member_phone) {
        this.member_phone = member_phone;
    }

    public String getMember_email() {
        return member_email;
    }

    public void setMember_email(String member_email) {
        this.member_email = member_email;
    }

    public Date getMember_date() {
        return member_date;
    }

    public void setMember_date(Date member_date) {
        this.member_date = member_date;
    }
}
