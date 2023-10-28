package com.dunowljj.book.domain.events.ticket;

import com.dunowljj.book.domain.events.event.Event;
import com.dunowljj.book.domain.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class TicketReservation {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ticket_reservation_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

//    @OneToOne(fetch = LAZY)
//    private TicketPayment ticketPayment;

    @Column(nullable = false)
    private Long price;

    @Column(nullable = false)
    private Long amount;

    @Column(nullable = false)
    private LocalDate reservationDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReservationStatus reservationStatus;

    @Builder
    public TicketReservation(Long id, Event event, User user, Long price, Long amount, LocalDate reservationDate, ReservationStatus reservationStatus) {
        this.id = id;
        this.event = event;
        this.user = user;
        this.price = price;
        this.amount = amount;
        this.reservationDate = reservationDate;
        this.reservationStatus = reservationStatus;
    }

    public void complete() {
        reservationStatus = ReservationStatus.COMP;
    }

    public void cancel() {
        this.reservationStatus = ReservationStatus.CANCEL;
    }
}
