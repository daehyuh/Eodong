package com.example.eodong.repository;

import com.example.eodong.domain.Member;
import com.example.eodong.domain.MemberMajor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
@Repository
public class JPAMemberMajorRepository implements MemberMajorRepository {
    public final EntityManager em;

    @Autowired
    public JPAMemberMajorRepository(EntityManager em){
        this.em = em;
    }

    @Override
    public MemberMajor save(MemberMajor memberMajor) {
        em.persist(memberMajor);
        return memberMajor;
    }

    @Override
    public List<MemberMajor> findAll() {
        List<MemberMajor> result =  em.createQuery("select m from MemberMajor m", MemberMajor.class)
                .getResultList();
        return result;
    }
}
