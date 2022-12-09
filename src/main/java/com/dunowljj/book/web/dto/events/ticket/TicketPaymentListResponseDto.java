package com.dunowljj.book.web.dto.events.ticket;

import com.dunowljj.book.domain.events.ticket.PayGroup;
import com.dunowljj.book.domain.events.ticket.PayStatus;
import com.dunowljj.book.domain.events.ticket.TicketPayment;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class TicketPaymentListResponseDto {
    private Long id;
    private String eventName;
    private Long reservationId;
    private Long price;
    private Long amount;
    private Long total;
    private PayGroup payGroup;
    private LocalDate payDate;
    private PayStatus payStatus;

    // todo : 기차놀이하는 get, 정리해야하나? 실수없이 작성을 도울 방법이 없을까?
    public TicketPaymentListResponseDto(TicketPayment entity) {
        this.id = entity.getId();
        this.eventName = entity.getTicketReservation().getEvent().getName();
        this.reservationId = entity.getTicketReservation().getId();
        this.price = entity.getTicketReservation().getPrice();
        this.amount = entity.getTicketReservation().getAmount();
        this.total = entity.getTotal();
        this.payGroup = entity.getPayGroup();
        this.payDate = entity.getPayDate();
        this.payStatus = entity.getPayStatus();
    }
}
