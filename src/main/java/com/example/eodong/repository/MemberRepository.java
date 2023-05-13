package com.example.eodong.repository;
import com.example.eodong.domain.Member;

import java.util.List;

public interface MemberRepository {

    Member save(Member member); // 회원가입
    List<Member> findAll();     // 전체조회
    List<Member> findByMemberId(String id); // ID로 검색
    List<Member> findByMemberEmail(String email); // Email로 검색
    List<Member> findByMemberNameAndMemberEmail(String name, String email); //이름과 이메일로 검색

}