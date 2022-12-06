package com.dunowljj.book.web.events.ticket;

import com.dunowljj.book.service.events.ticket.TicketReservationService;
import com.dunowljj.book.web.dto.events.ticket.TicketReserveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/events")
public class TicketApiController {

    private final TicketReservationService reservationService;

    @PostMapping("/ticket/reserve")
    public Long reserve(@RequestBody TicketReserveRequestDto requestDto) {
       return reservationService.reserve(requestDto);
    }
}
