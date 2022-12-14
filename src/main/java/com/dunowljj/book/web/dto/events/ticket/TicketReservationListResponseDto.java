package com.dunowljj.book.web.dto.events.ticket;

import com.dunowljj.book.domain.events.ticket.ReservationStatus;
import com.dunowljj.book.domain.events.ticket.TicketReservation;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class TicketReservationListResponseDto {

    private Long id;
    private Long price;
    private Long amount;
    private String eventName;
    private Long eventId;
    private LocalDate reservationDate;
    private ReservationStatus reservationStatus;

    public TicketReservationListResponseDto(TicketReservation entity) {
        this.id = entity.getId();
        this.price = entity.getPrice();
        this.amount = entity.getAmount();
        this.eventName = entity.getEvent().getName();
        this.eventId = entity.getEvent().getId();
        this.reservationDate = entity.getReservationDate();
        this.reservationStatus = entity.getReservationStatus();
    }
}
