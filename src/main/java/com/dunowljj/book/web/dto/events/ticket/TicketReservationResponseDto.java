package com.dunowljj.book.web.dto.events.ticket;

import com.dunowljj.book.domain.events.ticket.TicketReservation;
import lombok.Getter;

@Getter
public class TicketReservationResponseDto {
    private Long id;
    private Long price;
    private Long amount;
    private Long total;

    public TicketReservationResponseDto(TicketReservation entity) {
        this.id = entity.getId();
        this.price = entity.getPrice();
        this.amount = entity.getAmount();
        this.total = price * amount;
    }
}
