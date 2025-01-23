package com.example.jpashop.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.jpashop.domain.Item;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    // 항목 등록
    public void save(Item item) {
        em.persist(item);
    }

    public Item findById(Long id) {
        return em.find(Item.class, id);
    }

    public List<Item> findAll() {
        String qlString = "SELECT i FROM Item as i ORDER BY i.id ASC";
        return em.createQuery(qlString, Item.class).getResultList();
    }

}
