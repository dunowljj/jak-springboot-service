package com.dunowljj.book.web.events.hall;

import com.dunowljj.book.service.events.hall.HallService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/hall/list")
    public String list(Model model) {
        model.addAttribute("hallList", hallService.findAllDesc());
        return "events/hall/hall-list";
    }

    @GetMapping("/hall/update/{id}")
    public String update(@PathVariable Long id, Model model) {
        model.addAttribute("hall", hallService.findById(id));
        return "events/hall/hall-update";
    }
}