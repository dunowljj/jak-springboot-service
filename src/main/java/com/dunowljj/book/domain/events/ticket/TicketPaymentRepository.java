package com.dunowljj.book.domain.events.ticket;

import com.dunowljj.book.web.dto.events.ticket.TicketPaymentResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TicketPaymentRepository extends JpaRepository<TicketPayment, Long> {

    @Query("SELECT tp FROM TicketPayment tp ORDER BY tp.id DESC")
    public List<TicketPaymentResponseDto> findAllDESC();
}
