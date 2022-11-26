package com.dunowljj.book.domain.events;

import com.dunowljj.book.domain.BaseTimeEntity;
import com.dunowljj.book.domain.hall.Hall;
import com.dunowljj.book.domain.ticket.TicketReservation;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Events extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = IDENTITY)
//    @Column(name = "events_id")
    private Long id;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "hall_id")
    private Hall hall;

    @OneToMany(mappedBy = "events")
    private List<TicketReservation> ticketReservations = new ArrayList<>();

    @OneToMany(mappedBy = "events")
    private List<EventsRegistration> eventsRegistrations = new ArrayList<>();

    @Column(length = 500, nullable = false)
    private String name;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String detail;

    @Column(nullable = false)
    private Long price;

    @Column(nullable = false)
    private Long recruitAmount;

    @Column(nullable = false)
    private LocalDateTime startDate;
    @Column(nullable = false)
    private LocalDateTime endDate;

    @Column(nullable = false)
    private LocalDateTime recruitStartDate;

    @Column(nullable = false)
    private LocalDateTime recruitEndDate;

    @Column(nullable = false)
    private String field;

    private Long hitCount;

    @Builder
    public Events(Long id, Hall hall, List<TicketReservation> ticketReservations, List<EventsRegistration> eventsRegistrations, String name, String detail, Long price, Long recruitAmount,
                  LocalDateTime startDate, LocalDateTime endDate, LocalDateTime recruitStartDate, LocalDateTime recruitEndDate,
                  String field, Long hitCount) {
        this.id = id;
        this.hall = hall;
        this.ticketReservations = ticketReservations;
        this.eventsRegistrations = eventsRegistrations;
        this.name = name;
        this.detail = detail;
        this.price = price;
        this.recruitAmount = recruitAmount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.recruitStartDate = recruitStartDate;
        this.recruitEndDate = recruitEndDate;
        this.field = field;
        this.hitCount = hitCount;
    }
}
