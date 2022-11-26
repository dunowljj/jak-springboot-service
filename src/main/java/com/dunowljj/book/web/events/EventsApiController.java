package com.dunowljj.book.web.events;

import com.dunowljj.book.service.events.EventsService;
import com.dunowljj.book.web.dto.events.EventsRegisterRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class EventsApiController {

    private final EventsService eventsService;

    @PostMapping("/events/new")
    public Long register(@RequestBody EventsRegisterRequestDto requestDto) {
        return eventsService.save(requestDto);
    }
}
