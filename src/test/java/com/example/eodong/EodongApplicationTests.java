package com.example.eodong;

import com.example.eodong.domain.Member;
import com.example.eodong.domain.MemberMajor;
import com.example.eodong.service.MemberMajorService;
import com.example.eodong.service.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

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
        MemberMajor memberMajor = new MemberMajor();
        memberMajor.setMember_major("ddd");
        memberMajorService.save(memberMajor);
    }

    @Test
    void contextLoads3() {
        Member member = new Member();
        member.setMemberMajor("컴퓨터공학부");
        member.setMemberId("3");
        member.setMemberPw("a12131415");
        member.setMemberName("3");
        member.setMemberUniId("3");
        member.setMemberPhone("01038222413");
        member.setMemberEmail("3");
        member.setMemberDate(new Date());
        memberService.save(member);
    }

    @Test
        void 누가코드를영어로짜() {
        List<Member> list = memberService.findByMemberId("누군데");

        System.out.println(list.get(0).getMemberPw());
        }
    }
