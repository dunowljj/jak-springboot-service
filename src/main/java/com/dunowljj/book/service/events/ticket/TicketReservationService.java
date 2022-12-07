package com.dunowljj.book.service.events.ticket;

import com.dunowljj.book.domain.events.event.Event;
import com.dunowljj.book.domain.events.event.EventRegistration;
import com.dunowljj.book.domain.events.event.EventRegistrationRepository;
import com.dunowljj.book.domain.events.event.EventRepository;
import com.dunowljj.book.domain.events.ticket.TicketReservation;
import com.dunowljj.book.domain.events.ticket.TicketReservationRepository;
import com.dunowljj.book.web.dto.events.ticket.TicketReserveListResponseDto;
import com.dunowljj.book.web.dto.events.ticket.TicketReserveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TicketReservationService {

    private final TicketReservationRepository reservationRepository;
    private final EventRegistrationRepository eventRegistrationRepository;
    private final EventRepository eventRepository;

    @Transactional
    public Long reserve(TicketReserveRequestDto requestDto) {
        Long eventId = requestDto.getEventId();
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("해당 행사가 존재하지 않습니다. id="+ eventId));

        EventRegistration registration = eventRegistrationRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("해당 행사등록정보가 존재하지 않습니다. id=" + eventId));

       return reservationRepository.save(requestDto.toEntity(event, registration.getMember())).getId();
    }

    @Transactional
    public List<TicketReserveListResponseDto> findAllDESC() {
        return reservationRepository.findAllDESC().stream()
                .map(TicketReserveListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        TicketReservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 행사예약정보가 존재하지 않습니다. id=" + id));

        reservationRepository.delete(reservation);
        return;
    }
}
