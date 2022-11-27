package com.dunowljj.book.web.events.event;


import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@NoArgsConstructor
@RequestMapping("/events")
@Controller
public class EventController {

    @GetMapping("/event/save")
    public String save() {
        return "events/event/event-save";
    }

    @GetMapping("/event/update")
    public String update() {
        return "events/event/event-update";
    }
}
