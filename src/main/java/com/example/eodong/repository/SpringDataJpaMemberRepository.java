package com.example.eodong.repository;

import com.example.eodong.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.servlet.http.HttpSession;
import java.util.List;


public interface SpringDataJpaMemberRepository extends JpaRepository<Member,Long>, MemberRepository {


}