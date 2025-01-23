package com.example.jpashop.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long id;

    @Column(name = "order_price")
    private int orderPrice;

    private int count;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    public void setOrder(Order order) {
        System.out.println("------------------------------------" + order);
        this.order = order;
    }

    // 비즈니스 메소드
    // Orderitem 생성 : 팩토리 패턴

    public static OrderItem createOrderItem(int count, int oderPrice, Item item) {

        OrderItem orderItem = new OrderItem();
        orderItem.setCount(count);
        orderItem.setOrderPrice(oderPrice);
        orderItem.setItem(item);

        // 상품의 재고량을 줄인다.
        item.removeStock(count);

        return orderItem;

    }

    public int getTotalPrice() {
        return this.count * this.orderPrice;
    }

    // 주문 취소 시 상품의 재고량을 증가한다.
    public void cancel() {
        item.addStock(count);
    }

}
