package com.example.eodong.controller;

import com.example.eodong.controller.emailAuth.EmailService;
import com.example.eodong.domain.Member;
import com.example.eodong.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class MemberController {
    public final MemberService memberService;
    public final EmailService emailService;
    @Autowired
    public MemberController(MemberService memberService, EmailService emailService){
        this.memberService = memberService;
        this.emailService = emailService;
    }

    /**
     @모두 찾아서 json으로 찾기
     */
    @GetMapping(value = "/api/list")
    public String findAll(Model model) {
        List<Member> members = memberService.findAll();
        model.addAttribute("members", members);
        return "members/memberlist";
    }
    /**
     @모두 찾아서 json으로 찾기
     */
    @GetMapping(value = "/api/member")
    @ResponseBody
    public List<Member> findAllRB() {
        List<Member> members = memberService.findAll();
        return members;
    }

    /**
     * 
     * @프론트에게 줄 api들
     */
    
    @GetMapping(value = "/")
    public String home() {
        return "home";
    }
    @GetMapping(value = "/api")
    public String home2() {
        return "home";
    }

    @GetMapping(value = "/api/join")
    public String createMemberForm() {
        return "members/join";
    }
    @PostMapping(value = "/api/join")
    public String save(@Valid @ModelAttribute MemberForm form) {
        Member member = new Member();
        member.setMemberMajor(form.MEMBER_MAJOR);
        member.setMemberId(form.MEMBER_ID);
        member.setMemberPw(form.MEMBER_PW);
        member.setMemberName(form.MEMBER_NAME);
        member.setMemberUniId(form.MEMBER_UNI_ID);
        member.setMemberPhone(form.MEMBER_PHONE);
        member.setMemberEmail(form.MEMBER_EMAIL);
        member.setMemberDate(new Date());
        System.out.println(form.toString());
        memberService.save(member);
        return "home";
    }

    @GetMapping(value = "/api/login")
    public String login() {
        return "members/login";
    }
    @PostMapping(value = "/api/login")
    @ResponseBody
    public String login(@ModelAttribute LoginForm form, HttpSession session){
        String res = memberService.login(form.MEMBER_ID, form.MEMBER_PW, session);
        return res;
    }

    @GetMapping(value = "/api/findId")
    public String findId(){
        return "members/findId";
    }


    @PostMapping(value = "/api/findId")
    @ResponseBody
    public String findByMemberNameAndMemberEmail(@ModelAttribute MemberForm form){
        System.out.println(form.toString());
        String res ="";
        if (!memberService.findByMemberNameAndMemberEmail(form.MEMBER_NAME, form.MEMBER_EMAIL).isEmpty()){
            return memberService.findByMemberNameAndMemberEmail(form.MEMBER_NAME, form.MEMBER_EMAIL).get().getMemberPw();
        } else {
            return "아이디가 없음";
        }
    }

    @GetMapping(value = "/api/findPw")
    public String findPw(){
        return "members/findPw";
    }

    @PostMapping(value = "/api/findPw")
    @ResponseBody
    public String findPw(@ModelAttribute MemberForm form) throws Exception {
        System.out.println(form.toString());
        String confirm = emailService.sendSimpleMessage(form.MEMBER_EMAIL);

        String message = "<script>alert('이메일로 임시비밀번호를 보냈습니다');location.href='/';</script>";
        return message;
    }

    @GetMapping(value = "/api/updatePw")
    public String updatePw() {
        return "members/updatePw";
    }

    @PostMapping(value = "/api/updatePw")
    @ResponseBody
    public String updatePw(@ModelAttribute updateLoginForm form) {
        String message = memberService.updatePwManual(form.MEMBER_ID, form.MEMBER_PW, form.MEMBER_NewPW);
        return message;
    }

    @GetMapping(value = "/api/logout")
    public String login(HttpSession session){
        memberService.logout(session);
        return "home";
    }

    @GetMapping(value = "/api/chekcEmail")
    @ResponseBody
    public Boolean chekcEmail(@ModelAttribute MemberForm form) {
        // 없는것이기에 Not 붙여줌
        return !memberService.existsByMemberEmail(form.MEMBER_EMAIL);
    }

}
