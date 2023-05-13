package com.example.eodong.repository;

import com.example.eodong.domain.MemberMajor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface SpringDataJpaMemberMajarRepository extends JpaRepository<MemberMajor,String>, MemberMajorRepository {



}