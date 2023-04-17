package com.example.eodong.repository;

import com.example.eodong.domain.MemberMajor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataJpaMemberMajarRepository extends JpaRepository<MemberMajor,String>, MemberMajorRepository {

}