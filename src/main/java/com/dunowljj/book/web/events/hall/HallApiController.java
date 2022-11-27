package com.dunowljj.book.web.events.hall;

import com.dunowljj.book.service.events.hall.HallService;
import com.dunowljj.book.web.dto.events.hall.HallSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/events")
public class HallApiController {

    private final HallService hallService;

    @PostMapping("/hall/new")
    public Long register(@RequestBody HallSaveRequestDto requestDto) {
        return hallService.save(requestDto);
    }
}
