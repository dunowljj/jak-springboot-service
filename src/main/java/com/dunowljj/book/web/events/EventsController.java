package com.dunowljj.book.web.events;

import com.dunowljj.book.security.oauth.LoginUser;
import com.dunowljj.book.security.oauth.SessionUser;
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

    @GetMapping("/events/event")
    public String event(Model model,  @LoginUser SessionUser user) {

        if (user != null) {
            model.addAttribute("userName", user.getName());
        }

        return "events/event/home";
    }

    @GetMapping("/events/hall")
    public String hall(Model model, @LoginUser SessionUser user) {

        if (user != null) {
            model.addAttribute("userName", user.getName());
        }

        return "events/hall/home";
    }

    @GetMapping("/events/ticket")
    public String ticket(Model model,  @LoginUser SessionUser user) {

        if (user != null) {
            model.addAttribute("userName", user.getName());
        }

        return "events/ticket/home";
    }
}
