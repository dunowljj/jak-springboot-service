package com.dunowljj.book.web.events.event;


import com.dunowljj.book.domain.events.event.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/events")
@Controller
public class EventController {

    private final EventRepository eventRepository;

    @GetMapping("/event/save")
    public String save() {
        return "events/event/event-save";
    }

    @GetMapping("/event/update")
    public String update() {
        return "events/event/event-update";
    }

    @GetMapping("event/list")
    public String list(Model model) {
        model.addAttribute("eventList", eventRepository.findAllDESC());
        return "events/event/event-list";
    }
}
