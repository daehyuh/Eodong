package com.example.eodong.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "MEMBERMAJOR")
public class MemberMajor {
    @Id
    private String member_major;

    public String getMember_major() {
        return member_major;
    }

    public void setMember_major(String member_major) {
        this.member_major = member_major;
    }
}