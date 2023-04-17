package com.example.eodong.repository;

import com.example.eodong.domain.Member;
import com.example.eodong.domain.MemberMajor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member,Long>, MemberRepository {

}