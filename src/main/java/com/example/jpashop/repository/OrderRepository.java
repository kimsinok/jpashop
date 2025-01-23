package com.example.jpashop.repository;

import org.springframework.stereotype.Repository;

import com.example.jpashop.domain.Order;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;

    // 주문 등록
    public void save(Order order) {
        em.persist(order);
    }

    public Order findById(Long id) {
        return em.find(Order.class, id);
    }

}
