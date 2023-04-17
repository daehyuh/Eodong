package com.example.eodong.repository;


import com.example.eodong.domain.MemberMajor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

//@Repository
//@Transactional
public class JPAMemberMajorRepository implements MemberMajorRepository {

    private final EntityManager em;
//    @Autowired
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
        return em.createQuery("select m from MemberMajor m", MemberMajor.class)
                .getResultList();
    }
}
