package com.example.eodong.repository;

import com.example.eodong.domain.Member;

import java.util.List;

public interface MemberRepository {
    Member save(Member member);
    List<Member> findAll();
}