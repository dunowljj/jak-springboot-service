package com.dunowljj.book.domain.events.ticket;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.time.LocalDate;

import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.IDENTITY;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class TicketPayment {

    @Id @GeneratedValue(strategy = IDENTITY)
//    @Column(name = "ticket_payment")
    private Long id;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "ticket_reservation_id")
    private TicketReservation ticketReservation;

    @Column(nullable = false)
    private LocalDate payDate;
    @Column(nullable = false)
    private Long total;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PayGroup payGroup;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PayStatus payStatus;

    @Builder
    public TicketPayment(Long id, TicketReservation ticketReservation, LocalDate payDate, Long total, PayGroup payGroup, PayStatus payStatus) {
        this.id = id;
        this.ticketReservation = ticketReservation;
        this.payDate = payDate;
        this.total = total;
        this.payGroup = payGroup;
        this.payStatus = payStatus;
    }
}
