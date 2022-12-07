package com.dunowljj.book.domain.events.ticket;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TicketReservationRepository extends JpaRepository<TicketReservation, Long> {

    @Query("SELECT tr from TicketReservation tr ORDER BY tr.id DESC")
    List<TicketReservation> findAllDESC();

}
