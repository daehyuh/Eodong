package com.example.eodong.repository;

import com.example.eodong.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface SpringDataJpaMemberRepository extends JpaRepository<Member,Long>, MemberRepository {


}