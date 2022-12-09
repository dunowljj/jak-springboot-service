package com.dunowljj.book.domain.events.ticket;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TicketPaymentRepository extends JpaRepository<TicketPayment, Long> {

    @Query("SELECT tp FROM TicketPayment tp JOIN FETCH tp.ticketReservation tr JOIN FETCH tr.event ORDER BY tp.id DESC")
    public List<TicketPayment> findAllCompDESC();
}
