package com.example.eodong.service;

import com.example.eodong.domain.Member;
import com.example.eodong.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        return member.getMember_idx();
    }



    public List<Member> findAll(){
        return memberRepository.findAll();
    }


}
