package com.dunowljj.book.web.events.ticket;

import com.dunowljj.book.service.events.ticket.TicketReservationService;
import com.dunowljj.book.web.dto.events.ticket.TicketReserveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/events")
public class TicketApiController {

    private final TicketReservationService reservationService;

    @PostMapping("/ticket/reserve")
    public Long reserve(@RequestBody TicketReserveRequestDto requestDto) {
       return reservationService.reserve(requestDto);
    }

    @DeleteMapping("/ticket/{id}/ticket-reserve")
    public Long cancel(@PathVariable Long id) {
        reservationService.delete(id);
        return id;
    }
}
