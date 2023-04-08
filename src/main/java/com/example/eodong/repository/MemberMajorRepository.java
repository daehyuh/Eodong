package com.example.eodong.repository;

import com.example.eodong.domain.MemberMajor;

import java.util.List;

public interface MemberMajorRepository {
    MemberMajor save(MemberMajor memberMajor);
    List<MemberMajor> findAll();

}
