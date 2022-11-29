package com.dunowljj.book.web.events.event;


import com.dunowljj.book.service.events.event.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/events")
@Controller
public class EventController {

    private final EventService eventService;

    @GetMapping("/event/save")
    public String save() {
        return "events/event/event-save";
    }

    @GetMapping("/event/{id}")
    public String update(@PathVariable Long id, Model model) {
        model.addAttribute("event", eventService.findById(id));
        return "events/event/event-update";
    }

    @GetMapping("/event/list")
    public String list(Model model) {
        model.addAttribute("eventList", eventService.findAllDESC());
        return "events/event/event-list";
    }
}
