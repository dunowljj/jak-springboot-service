package com.dunowljj.book.web.events.hall;

import com.dunowljj.book.service.events.hall.HallService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/events")
public class HallController {

    private final HallService hallService;

    @GetMapping("/hall/save")
    public String save() {
        return "events/hall/hall-save";
    }
}