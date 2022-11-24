package com.dunowljj.book.web.events;

import com.dunowljj.book.config.auth.LoginUser;
import com.dunowljj.book.config.auth.SessionUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EventsController {

    @GetMapping("/events")
    public String home(Model model,  @LoginUser SessionUser user) {

        if (user != null) {
            model.addAttribute("userName", user.getName());
        }

        return "events/home";
    }

    @GetMapping("/events/reservation")
    public String reservation(Model model,  @LoginUser SessionUser user) {

        if (user != null) {
            model.addAttribute("userName", user.getName());
        }

        return "events/reservation";
    }

    @GetMapping("/events/registration")
    public String registration(Model model,  @LoginUser SessionUser user) {

        if (user != null) {
            model.addAttribute("userName", user.getName());
        }

        return "events/registration";
    }
}
