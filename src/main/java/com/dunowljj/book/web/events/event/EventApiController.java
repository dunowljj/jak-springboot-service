package com.dunowljj.book.web.events.event;

import com.dunowljj.book.service.events.event.EventService;
import com.dunowljj.book.web.dto.events.event.EventSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/events")
public class EventApiController {

    private final EventService eventService;

    @PostMapping("/event/new")
    public Long save(@RequestBody EventSaveRequestDto requestDto) {
        return eventService.save(requestDto);
    }
}
