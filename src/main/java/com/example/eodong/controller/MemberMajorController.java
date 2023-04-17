package com.example.eodong.controller;

import com.example.eodong.domain.Member;
import com.example.eodong.domain.MemberMajor;
import com.example.eodong.service.MemberMajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Controller
public class MemberMajorController {

    public final MemberMajorService memberMajorService;
    @Autowired
    public MemberMajorController(MemberMajorService memberMajorService){
        this.memberMajorService = memberMajorService;
    }

    @GetMapping(value = "/api/major")
    @ResponseBody
    public List<MemberMajor> apimajor() {
        List<MemberMajor> members = memberMajorService.findAll();
        return members;
    }

    @GetMapping(value = "/api/majorList")
    public String apimajorlist(Model model) {
        List<MemberMajor> members = memberMajorService.findAll();
        model.addAttribute("members", members);
        return "members/majorlist";
    }

    @GetMapping(value = "/api/majorJoin")
    public String apimajorJoin() {
        return "members/major";
    }

    @PostMapping(value = "/api/newMajor")
    public String newMajor(@ModelAttribute MemberMajorForm form) {
        MemberMajor memberMajor = new MemberMajor();
        memberMajor.setMember_major(form.member_major);
        System.out.println(form.member_major);
        memberMajorService.save(memberMajor);
        return "home";
    }



}
