package com.example.eodong.domain;

import javax.persistence.*;
@Table(name = "MEMBERMAJOR")
@Entity
public class MemberMajor {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public String member_major;

    public String getMember_major() {
        return member_major;
    }

    public void setMember_major(String member_major) {
        this.member_major = member_major;
    }
}
