package com.example.eodong;

import com.example.eodong.domain.Member;
import com.example.eodong.service.MemberMajorService;
import com.example.eodong.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
class EodongApplicationTests {

    public final MemberMajorService memberMajorService;
    public final MemberService memberService;
    @Autowired
    public EodongApplicationTests(MemberMajorService memberMajorService, MemberService memberService){
        this.memberMajorService = memberMajorService;
        this.memberService = memberService;
    }

    @Test
    void contextLoads() {
        memberService.findAll();
    }

    @Test
    void contextLoads2() {
        memberMajorService.findAll();
    }

    @Test
    void contextLoads3() {
        Member member = new Member();
        member.setMember_major("컴퓨터공학부");
        member.setMember_id("3");
        member.setMember_pw("3");
        member.setMember_name("3");
        member.setMember_uni_id(3);
        member.setMember_phone(3);
        member.setMember_email("3");
        member.setMember_date(new Date());
        memberService.save(member);
    }
}
