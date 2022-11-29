package com.dunowljj.book.web.events.hall;

import com.dunowljj.book.service.events.hall.HallService;
import com.dunowljj.book.web.dto.events.hall.HallSaveRequestDto;
import com.dunowljj.book.web.dto.events.hall.HallUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/events")
public class HallApiController {

    private final HallService hallService;

    @PostMapping("/hall/new")
    public Long register(@RequestBody HallSaveRequestDto requestDto) {
        return hallService.save(requestDto);
    }

    @PutMapping("/hall/{id}")
    public Long update(@PathVariable Long id, @RequestBody HallUpdateRequestDto requestDto) {
        return hallService.update(id, requestDto);
    }

    @DeleteMapping("/hall/{id}")
    public Long delete(@PathVariable Long id) {
        hallService.delete(id);
        return id;
    }
}
