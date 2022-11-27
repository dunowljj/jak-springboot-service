package com.dunowljj.book.service.events.event;

import com.dunowljj.book.domain.events.event.EventRepository;
import com.dunowljj.book.domain.events.hall.Hall;
import com.dunowljj.book.domain.events.hall.HallRepository;
import com.dunowljj.book.web.dto.events.event.EventSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class EventService {

    private final EventRepository eventRepository;

    private final HallRepository hallRepository;

    @Transactional
    public Long save(EventSaveRequestDto requestDto) {
        Long hallId = requestDto.getHallId();
        Hall hall = hallRepository.findById(hallId)
                .orElseThrow(() -> new IllegalArgumentException("해당 행사장이 존재하지 않습니다. id =" + hallId));
        return eventRepository.save(requestDto.toEntity(hall)).getId();
    }
}
