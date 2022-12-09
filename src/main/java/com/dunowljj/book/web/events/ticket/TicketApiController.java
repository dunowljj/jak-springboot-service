package com.dunowljj.book.web.events.ticket;

import com.dunowljj.book.service.events.ticket.TicketPaymentService;
import com.dunowljj.book.service.events.ticket.TicketReservationService;
import com.dunowljj.book.web.dto.events.ticket.TicketPaymentSaveRequestDto;
import com.dunowljj.book.web.dto.events.ticket.TicketReservationRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/events")
public class TicketApiController {

    private final TicketReservationService reservationService;
    private final TicketPaymentService paymentService;

    @PostMapping("/ticket/reserve")
    public Long reserve(@RequestBody TicketReservationRequestDto requestDto) {
        return reservationService.reserve(requestDto);
    }

    @DeleteMapping("/ticket/{id}/reserve")
    public Long cancel(@PathVariable Long id) {
        reservationService.cancel(id);
        return id;
    }

    // todo: 리소스 네이밍 : id를 어떨때 뺴야하는가?
    @PostMapping("ticket/pay")
    public Long pay(@RequestBody TicketPaymentSaveRequestDto requestDto) {
        return paymentService.pay(requestDto);
    }

    @PutMapping("ticket/{id}/pay")
    public Long refund(@PathVariable Long id) {
        return paymentService.refund(id);
    }
}
