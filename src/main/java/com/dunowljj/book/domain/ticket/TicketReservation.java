package com.dunowljj.book.domain.ticket;

import com.dunowljj.book.domain.events.Events;
import com.dunowljj.book.domain.user.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.time.LocalDate;

import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class TicketReservation {

    @Id @GeneratedValue(strategy = IDENTITY)
//    @Column(name = "ticket_reservation_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "events_id")
    private Events events;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne(fetch = LAZY)
    private TicketPayment ticketPayment;

    @Column(nullable = false)
    private Long price;


    @Column(nullable = false)
    private Long amount;

    @Column(nullable = false)
    private LocalDate reservationDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReservationStatus ticketStatus;

    @Builder
    public TicketReservation(Long id, Events events, Member member, Long price, Long amount, LocalDate reservationDate, ReservationStatus ticketStatus) {
        this.id = id;
        this.events = events;
        this.member = member;
        this.price = price;
        this.amount = amount;
        this.reservationDate = reservationDate;
        this.ticketStatus = ticketStatus;
    }
}
