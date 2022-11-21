package com.dunowljj.book.web.events;

import com.dunowljj.book.service.events.EventsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class EventsApiController {

    private final EventsService eventsService;
}
