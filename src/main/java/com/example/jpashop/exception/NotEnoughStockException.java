package com.example.jpashop.exception;

public class NotEnoughStockException extends RuntimeException {

    public NotEnoughStockException() {
        super("재고 수량이 부족합니다.");
    }

    public NotEnoughStockException(String message) {
        super(message);
    }

}
