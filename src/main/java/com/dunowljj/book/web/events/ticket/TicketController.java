package com.dunowljj.book.web.events.ticket;

import com.dunowljj.book.service.events.event.EventService;
import com.dunowljj.book.service.events.ticket.TicketPaymentService;
import com.dunowljj.book.service.events.ticket.TicketReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/events")

public class TicketController {

    private final EventService eventService;
    private final TicketReservationService reservationService;
    private final TicketPaymentService paymentService;

    @GetMapping("/ticket/list")
    public String list(Model model) {
        model.addAttribute("eventList", eventService.findAllDESC());
        return "/events/ticket/ticket-list";
    }

    // todo : ticket-detail과 event-update의 중복
    @GetMapping("/ticket/detail/{id}")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("event", eventService.findById(id));
        return "/events/ticket/ticket-detail";
    }

    @GetMapping("/ticket/reserve/{id}")
    public String reserve(@PathVariable Long id, Model model) {
        model.addAttribute("event", eventService.findById(id));
        return "/events/ticket/ticket-reserve";
    }

    // todo : 자신의 예약만 보이도록 하기
    @GetMapping("/ticket/reserveList")
    public String reserveList(Model model) {
        model.addAttribute("reservationList", reservationService.findAllDESC());
        return "/events/ticket/ticket-reserveList";
    }

    // todo : 네이밍, 조회하지 않고 파라미터로 넘기기?
    @GetMapping("/ticket/pay/{id}")
    public String pay(@PathVariable Long id, Model model) {
        model.addAttribute("reservation", reservationService.findById(id));
        return "/events/ticket/ticket-pay";
    }

    @GetMapping("/ticket/payList")
    public String payList(Model model) {
        model.addAttribute("paymentList", paymentService.findAllDESC());
        return "/events/ticket/ticket-payList";
    }
}
