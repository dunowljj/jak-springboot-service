package com.dunowljj.book.web.dto.events.ticket;

import com.dunowljj.book.domain.events.event.Event;
import com.dunowljj.book.domain.events.ticket.ReservationStatus;
import com.dunowljj.book.domain.events.ticket.TicketReservation;
import com.dunowljj.book.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class TicketReservationRequestDto {
    private Long eventId;
    private Long price;
    private Long amount;
    private LocalDate reservationDate;
    private ReservationStatus reservationStatus = ReservationStatus.READY;

    @Builder
    public TicketReservationRequestDto(Long eventId, Long price, Long amount, LocalDate reservationDate) {
        this.eventId = eventId;
        this.price = price;
        this.amount = amount;
        this.reservationDate = reservationDate;
    }

    public TicketReservation toEntity(Event event, User user) {
        return TicketReservation.builder()
                .event(event)
                .user(user)
                .price(price)
                .amount(amount)
                .reservationDate(reservationDate)
                .reservationStatus(reservationStatus)
                .build();
    }
}


