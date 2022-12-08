package com.dunowljj.book.service.events.ticket;

import com.dunowljj.book.domain.events.ticket.TicketPaymentRepository;
import com.dunowljj.book.domain.events.ticket.TicketReservation;
import com.dunowljj.book.domain.events.ticket.TicketReservationRepository;
import com.dunowljj.book.web.dto.events.ticket.TicketPaymentSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class TicketPaymentService {

    private final TicketPaymentRepository paymentRepository;

    private final TicketReservationRepository reservationRepository;

    @Transactional
    public Long pay(TicketPaymentSaveRequestDto requestDto) {
        Long id = requestDto.getReservationId();
        TicketReservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 에약이 존재하지 않습니다. id=" + id));

        reservation.complete();

        return paymentRepository.save(requestDto.toEntity(reservation)).getId();
    }
}
