package com.example.jpashop.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.jpashop.domain.Member;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    // 회원 등록
    public void save(Member Member) {
        em.persist(Member);
    }

    // 회원 상세 조회
    public Member findById(Long id) {
        return em.find(Member.class, id);
    }

    // 회원 목록 조회
    public List<Member> findAll() {

        // JPQL
        String qlString = "SELECT m FROM Member AS m ORDER BY m.id DESC"; // DESC
        // String qlString = "selct m from Member as m";
        return em.createQuery(qlString, Member.class).getResultList();

    }

}
