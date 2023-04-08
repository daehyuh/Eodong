package com.example.eodong.controller;

import com.example.eodong.domain.Member;
import com.example.eodong.domain.MemberMajor;
import com.example.eodong.service.MemberMajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MemberMajorController {
    public final MemberMajorService memberMajorService;
    @Autowired
    public MemberMajorController(MemberMajorService memberMajorService){
        this.memberMajorService = memberMajorService;
    }

    @GetMapping(value = "/api/membermajors")
    @ResponseBody
    public List<MemberMajor> memberMajorfindAll(){
        List<MemberMajor> memberMajors = memberMajorService.findAll();
        return memberMajors;
    }

    @GetMapping(value = "/members/major")
    public String create2Form(Model model) {
        List<MemberMajor> members = memberMajorService.findAll();
        model.addAttribute("members", members);
        return "members/majorlist";
    }

    @PostMapping("/api/saveMajor")
    public String memberMajorSave(@ModelAttribute MemberMajorForm memberMajorForm){
        MemberMajor memberMajor = new MemberMajor();
        memberMajor.setMember_major(memberMajorForm.getMember_major());
        memberMajorService.save(memberMajor);
        return "home";
    }


}
