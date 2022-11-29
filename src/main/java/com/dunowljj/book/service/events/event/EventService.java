package com.dunowljj.book.service.events.event;

import com.dunowljj.book.domain.events.event.Event;
import com.dunowljj.book.domain.events.event.EventRepository;
import com.dunowljj.book.domain.events.hall.Hall;
import com.dunowljj.book.domain.events.hall.HallRepository;
import com.dunowljj.book.web.dto.events.event.EventListResponseDto;
import com.dunowljj.book.web.dto.events.event.EventResponseDto;
import com.dunowljj.book.web.dto.events.event.EventSaveRequestDto;
import com.dunowljj.book.web.dto.events.event.EventUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

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

    @Transactional(readOnly = true)
    public List<EventListResponseDto> findAllDESC() {
        return eventRepository.findAllDESC().stream()
                .map(EventListResponseDto::new)
                .collect(toList());
    }

    @Transactional
    public Long update(Long id, EventUpdateRequestDto requestDto) {
        Long hallId = requestDto.getHallId();
        Hall hall = hallRepository.findById(hallId)
                .orElseThrow(() -> new IllegalArgumentException("해당 행사장이 존재하지 않습니다. id =" + hallId));

        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 행사가 존재하지 않습니다. id =" + id));

        event.update(requestDto, hall);
        return id;
    }

    @Transactional
    public void delete(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 행사가 존재하지 않습니다. id =" + id));
        eventRepository.delete(event);
        return;
    }

    public EventResponseDto findById(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 행사가 존재하지 않습니다. id =" + id));
        return new EventResponseDto(event);
    }
}
