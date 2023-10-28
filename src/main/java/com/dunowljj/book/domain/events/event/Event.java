package com.dunowljj.book.domain.events.event;

import com.dunowljj.book.domain.BaseTimeEntity;
import com.dunowljj.book.domain.events.hall.Hall;
import com.dunowljj.book.domain.events.ticket.TicketReservation;
import com.dunowljj.book.web.dto.events.event.EventUpdateRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@NoArgsConstructor(access = PROTECTED)
@Getter
@Entity
public class Event extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "events_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "hall_id")
    private Hall hall;

    @OneToMany(mappedBy = "event")
    private List<TicketReservation> ticketReservations = new ArrayList<>();

    @OneToMany(mappedBy = "event")
    private List<EventRegistration> eventRegistrations = new ArrayList<>();

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
    public Event(Long id, Hall hall, List<TicketReservation> ticketReservations, List<EventRegistration> eventRegistrations, String name, String detail, Long price, Long recruitAmount,
                 LocalDateTime startDate, LocalDateTime endDate, LocalDateTime recruitStartDate, LocalDateTime recruitEndDate,
                 String field, Long hitCount) {
        this.id = id;
        this.hall = hall;
        this.ticketReservations = ticketReservations;
        this.eventRegistrations = eventRegistrations;
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

    public void update(EventUpdateRequestDto requestDto, Hall hall) {
        this.hall = hall;
        this.name = requestDto.getName();
        this.detail = requestDto.getDetail();
        this.price = requestDto.getPrice();
        this.recruitAmount = requestDto.getRecruitAmount();
        this.startDate = requestDto.getStartDate();
        this.endDate = requestDto.getEndDate();
        this.recruitStartDate = requestDto.getRecruitStartDate();
        this.recruitEndDate = requestDto.getRecruitEndDate();
        this.field = requestDto.getField();
    }
}
