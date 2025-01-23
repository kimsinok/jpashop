package com.example.jpashop.domain;

import java.util.ArrayList;
import java.util.List;

import com.example.jpashop.exception.NotEnoughStockException;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
public abstract class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    private String name;

    private int price;

    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    // 비즈니스 메소드

    // 상품의 재고수량을 증가하다.
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    // 상품의 재고수량을 감소하다.
    public void removeStock(int quantity) {

        int restQuantity = this.stockQuantity - quantity;

        if (restQuantity < 0) {
            throw new NotEnoughStockException("재고가 부족합니다.");
        }

        this.stockQuantity -= quantity;
    }

}
