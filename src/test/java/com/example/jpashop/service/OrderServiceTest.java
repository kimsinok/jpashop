package com.example.jpashop.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.example.jpashop.exception.NotEnoughStockException;

import static org.junit.jupiter.api.Assertions.*;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@Slf4j
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Test
    public void test() {
        assertNotNull(orderService);
    }

    @Test
    @Rollback(false)
    void testRegisterOrder() {

        // given
        Long memberId = 2L;
        Long itemId = 1L;
        int count = 5;

        // when
        Long orderId = orderService.registerOrder(memberId, itemId, count);

        // then
        assertNotNull(orderId);

    }

    @Test
    @Rollback(true)
    void testRegisterOrderException() {

        // given
        Long memberId = 2L;
        Long itemId = 1L;
        int count = 10;

        // when
        // then
        assertThrows(NotEnoughStockException.class, () -> {

            Long orderId = orderService.registerOrder(memberId, itemId, count);

        });

    }

    @Test
    @Rollback(false)
    public void testCancelOrder() {

        // given
        Long orderId = 2L;

        // when
        orderService.cancelOrder(orderId);

        // then

    }

    @Test
    @Rollback(true)
    public void testCancelOrder1() {

        // given
        Long orderId = 3L;

        // when
        // then
        assertThrows(IllegalStateException.class, () -> {
            orderService.cancelOrder(orderId);

        });

    }

}
