package com.example.eodong.controller;

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

@Controller
public class MemberController {
    public final MemberService memberService;
    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    /**
     */
    @GetMapping(value = "/api/list")
    public String findAll(Model model) {
        List<Member> members = memberService.findAll();
        model.addAttribute("members", members);
        return "members/memberlist";
    }
    /**
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
        return "home";
    }

    @GetMapping(value = "/api/findId")
    public String findId(){
        return "members/findId";
    }


    @PostMapping(value = "/api/findId")
    @ResponseBody
    public ResponseEntity findByMemberNameAndMemberEmail(@ModelAttribute MemberForm form){
        ResponseEntity responseEntity = new ResponseEntity();
        System.out.println(form.toString());
        String res ="";
        if (!memberService.findByMemberNameAndMemberEmail(form.MEMBER_NAME, form.MEMBER_EMAIL).isEmpty()){
            responseEntity.code = 200;
            responseEntity.message = memberService.findByMemberNameAndMemberEmail(form.MEMBER_NAME, form.MEMBER_EMAIL).get().getMemberId();
        return responseEntity;
        } else {
            responseEntity.code = 500;
            responseEntity.message = "아이디가 없음";
            return responseEntity;
        }
    }

    @GetMapping(value = "/api/findPw")
    public String findPw(){
        return "members/findPw";
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
    public ResponseEntity chekcEmail(@ModelAttribute MemberForm form) {
        // 없는것이기에 Not 붙여줌
        ResponseEntity responseEntity = new ResponseEntity();
        if (!memberService.existsByMemberEmail(form.MEMBER_EMAIL)) {
            responseEntity.code=200;
            responseEntity.message="중복 이메일 없음";
            return responseEntity;
        } else {
            responseEntity.code=500;
            responseEntity.message="중복 이메일 있음";
            return responseEntity;
        }
    }

    static class ResponseEntity {
        private int code;
        private String message;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

}
