package com.dunowljj.book.web.events.ticket;

import com.dunowljj.book.service.events.event.EventService;
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

    @GetMapping("/ticket/list")
    public String save(Model model) {
        model.addAttribute("eventList", eventService.findAllDESC());
        return "/events/ticket/ticket-list";
    }

    @GetMapping("/ticket/detail/{id}")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("event", eventService.findById(id));
        return "/events/ticket/ticket-detail";
    }

    @GetMapping("/ticket/reserve/{id}")
    private String reserve(@PathVariable Long id, Model model) {
        model.addAttribute("event", eventService.findById(id));
        return "/events/ticket/ticket-reserve";
    }
}
