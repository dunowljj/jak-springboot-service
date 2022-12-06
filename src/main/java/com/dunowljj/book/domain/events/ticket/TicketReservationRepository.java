package com.dunowljj.book.domain.events.ticket;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketReservationRepository extends JpaRepository<TicketReservation, Long> {
}
