package com.example.eodong.controller;

import com.example.eodong.domain.Member;
import com.example.eodong.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;

@Controller
public class MemberController {
    public final MemberService memberService;
    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    @GetMapping(value = "/")
    public String home() {
        return "home";
    }

    @GetMapping(value = "/members/join")
    public String memberjoinForm() {
        return "members/createMemberForm";
    }

    @GetMapping(value = "/members/list")
    public String memberlist(Model model) {
        List<Member> members = memberService.findAll();
        model.addAttribute("members", members);
        return "members/memberlist";
    }

    @GetMapping(value = "/api/member")
    @ResponseBody
    public List<Member> ApiMembers() {
        List<Member> members = memberService.findAll();
        return members;
    }


    @ResponseBody
    @GetMapping(value = "/api/test")
    public String test(Model model) {
        String message = "";
        if (message.equals("")){
            message = "<script>alert('탈퇴되었습니다. 자동 로그아웃 됩니다.');location.href='/';</script>";
        }else {
            message = "<script>alert('ej 탈퇴 에러');location.href='/';</script>";
        }
        return message;
    }

    @PostMapping(value = "/api/join")
    public String Apinew(@ModelAttribute MemberForm form) {
        Member member = new Member();
        member.setMemberMajor(form.MEMBER_MAJOR);
        member.setMemberId(form.MEMBER_ID);
        member.setMemberPw(form.MEMBER_PW);
        member.setMemberName(form.MEMBER_NAME);
        member.setMemberUniId(form.MEMBER_UNI_ID);
        member.setMemberPhone(form.MEMBER_PHONE);
        member.setMemberEmail(form.MEMBER_EMAIL);
        member.setMemberDate(new Date());
        memberService.save(member);
        return "home";
    }



}
