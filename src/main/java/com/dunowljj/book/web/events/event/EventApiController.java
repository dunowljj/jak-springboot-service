package com.dunowljj.book.web.events.event;

import com.dunowljj.book.config.auth.LoginUser;
import com.dunowljj.book.config.auth.SessionUser;
import com.dunowljj.book.service.events.event.EventService;
import com.dunowljj.book.web.dto.events.event.EventSaveRequestDto;
import com.dunowljj.book.web.dto.events.event.EventUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/events")
public class EventApiController {

    private final EventService eventService;

    @PostMapping("/event/new")
    public Long save(@RequestBody EventSaveRequestDto requestDto, @LoginUser SessionUser user) {
        return eventService.save(requestDto, user);
    }

    @PutMapping("/event/{id}")
    public Long update(@PathVariable Long id, @RequestBody EventUpdateRequestDto requestDto) {
        return eventService.update(id, requestDto);
    }

    @DeleteMapping("/event/{id}")
    public Long delete(@PathVariable Long id) {
        eventService.delete(id);
        return id;
    }
}
