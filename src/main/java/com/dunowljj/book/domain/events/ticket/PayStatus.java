package com.dunowljj.book.domain.events.ticket;

public enum PayStatus {
    READY("대기"), COMP("결제 완료"), REFUND("환불 완료");

    private final String name;

    PayStatus(String name) {
        this.name = name;
    }
}
