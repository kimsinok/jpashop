package com.example.jpashop.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CollectionId;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status; // ORDER, CANCEL

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    // 연관 관계 편의 메소드
    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }

    public void setMember(Member member) {
        this.member = member;
        member.getOders().add(this);
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItem.setOrder(this);
        this.orderItems.add(orderItem);

    }

    // 비즈니스 메소드

    // 주문 생성 : 팩토리 메소드 패턴
    public static Order createOrder(Member member, Delivery delivery, OrderItem... orderItems) {

        Order order = new Order();

        order.setMember(member);

        order.setDelivery(delivery);

        for (OrderItem orderItem : orderItems) {
            // order.orderItems.add(orderItem);
            order.addOrderItem(orderItem);
        }

        order.setStatus(OrderStatus.ORDER);

        order.setOrderDate(LocalDateTime.now());

        return order;
    }

    // 주문 취소
    public void cancelOrder() {

        if (this.delivery.getStatus() == DeliveryStatus.COMPLETE) {
            throw new IllegalStateException("이미 배송된 완료된 상품은 취소가 불가능합니다");
        }

        this.setStatus(OrderStatus.CANCEL);

        this.orderItems.forEach(orderItem -> {
            orderItem.cancel();
        });

    }

    // 주문 총 금액을 계산한다.
    public int getTotalPrice() {
        int total = 0;
        for (OrderItem orderItem : this.orderItems) {
            total += orderItem.getTotalPrice();
        }
        return total;
    }

}
