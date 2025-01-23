package com.example.jpashop.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.jpashop.domain.Delivery;
import com.example.jpashop.domain.DeliveryStatus;
import com.example.jpashop.domain.Item;
import com.example.jpashop.domain.Member;
import com.example.jpashop.domain.Order;
import com.example.jpashop.domain.OrderItem;
import com.example.jpashop.repository.ItemRepository;
import com.example.jpashop.repository.MemberRepository;
import com.example.jpashop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;

    private final MemberRepository memberRepository;

    private final ItemRepository itemRepository;

    // 주문 생성
    @Transactional(readOnly = false)
    public Long registerOrder(Long mmberId, Long itemId, int count) {

        Member member = memberRepository.findById(mmberId);

        Item item = itemRepository.findById(itemId);

        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());
        delivery.setStatus(DeliveryStatus.READY);

        OrderItem orderItem = OrderItem.createOrderItem(count, item.getPrice(), item);

        Order order = Order.createOrder(member, delivery, orderItem);

        orderRepository.save(order);

        return order.getId();

    }

    // 주문 취소
    @Transactional(readOnly = false)
    public void cancelOrder(long orderId) {

        Order order = orderRepository.findById(orderId);
        order.cancelOrder();

    }

}
