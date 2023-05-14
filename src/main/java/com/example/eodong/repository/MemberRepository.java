package com.example.eodong.repository;
import com.example.eodong.domain.Member;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface MemberRepository {

    Member save(Member member); // 회원가입
    List<Member> findAll();     // 전체조회
    List<Member> findByMemberId(String id); // ID로 검색
    List<Member> findByMemberEmail(String email); // Email로 검색
    List<Member> findByMemberNameAndMemberEmail(String name, String email); //이름과 이메일로 검색
    boolean existsByMemberId(String id);
    boolean existsByMemberEmail(String email);

    @Transactional
    @Modifying
    @Query("update Member m set m.memberPw = :pw where m.memberEmail = :email")
    void updatePw(String pw,String email);

    @Transactional
    @Modifying
    @Query("update Member m set m.memberPw = :newPw where m.memberId = :id and m.memberPw= :pw")
    void updatePwManual(String id, String pw, String newPw);


}