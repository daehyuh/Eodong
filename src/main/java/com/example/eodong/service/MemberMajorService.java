package com.example.eodong.service;

import com.example.eodong.domain.MemberMajor;
import com.example.eodong.repository.MemberMajorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class MemberMajorService {
    private final MemberMajorRepository memberMajorRepository;
    @Autowired
    public MemberMajorService(MemberMajorRepository memberMajorRepository){
        this.memberMajorRepository = memberMajorRepository;
    }

    public String save(MemberMajor memberMajor){
        memberMajorRepository.save(memberMajor);
        return memberMajor.member_major;
    }

    public List<MemberMajor> findAll(){
        return memberMajorRepository.findAll();
    }

}
