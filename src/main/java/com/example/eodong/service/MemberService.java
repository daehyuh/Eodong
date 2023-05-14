package com.example.eodong.service;

import com.example.eodong.domain.Member;
import com.example.eodong.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder){
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Long save(Member member){
//        validateDuplicateMember(member);

        member.setMemberPw(passwordEncoder.encode(member.getMemberPw()));

        memberRepository.save(member);
        return member.getMemberIdx();
    }

    public List<Member> findAll(){
        return memberRepository.findAll();
    }

    public List<Member> findByMemberId(String id){
        return memberRepository.findByMemberId(id);
    }

    public String findByMemberNameAndMemberEmail(String name, String email){
        return memberRepository.findByMemberNameAndMemberEmail(name, email).get(0).getMemberId();
    }

    public List<Member> findByMemberEmail(String email){
        return memberRepository.findByMemberEmail(email);
    }


    public String login(String id, String pw, HttpSession session) {
        String message = "";
        if (!memberRepository.existsByMemberId(id)){
            message = "<script>alert('로그인 실패했습니다.');history.back();</script>";
            return message;
        } else{
            List<Member> member = memberRepository.findByMemberId(id);
            Member member1 = member.get(0);
            String encodedPassword = member1.getMemberPw();
            if (passwordEncoder.matches(pw, encodedPassword)){
                session.setAttribute("userid", member1.getMemberId());
                session.setAttribute("name", member1.getMemberName());
                message = "<script>alert('로그인 되었습니다.');location.href='/';</script>";
                return message;
            } else {
                message = "<script>alert('로그인 실패했습니다.');history.back();</script>";
                return message;
            }
        }
    }


    public void updatePw(String pw,String email){
        memberRepository.updatePw(pw, email);
    }

    public String updatePwManual(String id, String pw, String newPw){
        String message = "";
        if (!memberRepository.existsByMemberId(id)){
            message = "<script>alert('아이디가 없습니다.');history.back();</script>";
            return message;
        } else{
            List<Member> member = memberRepository.findByMemberId(id);
            Member member1 = member.get(0);
            String encodedPassword = member1.getMemberPw();
            if (passwordEncoder.matches(pw, encodedPassword)){
                newPw = passwordEncoder.encode(newPw);
                memberRepository.updatePwManual(id, pw, newPw);
                message = "<script>alert('비밀번호가 변경 완료.');location.href='/';</script>";
                return message;
            } else {
                message = "<script>alert('비밀번호 변경 실패.');history.back();</script>";
                return message;
            }
        }
    }

    public void logout(HttpSession session) {
        session.invalidate(); // 세션 초기화
    }

    public boolean existsByMemberEmail(String email){
        return memberRepository.existsByMemberEmail(email);
    }

}
