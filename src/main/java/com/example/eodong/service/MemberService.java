package com.example.eodong.service;

import com.example.eodong.domain.Member;
import com.example.eodong.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Struct;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    public Long save(Member member){
//        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getMemberIdx();
    }

    public List<Member> findAll(){
        return memberRepository.findAll();
    }

    public List<Member> findByMemberId(String id){
        return memberRepository.findByMemberId(id);
    }



}
