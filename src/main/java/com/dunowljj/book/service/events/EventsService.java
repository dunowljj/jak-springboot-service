package com.dunowljj.book.service.events;

import com.dunowljj.book.domain.events.EventsRepository;
import com.dunowljj.book.domain.hall.Hall;
import com.dunowljj.book.domain.hall.HallRepository;
import com.dunowljj.book.web.dto.events.EventsRegisterRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class EventsService {

    private final EventsRepository eventsRepository;

    private final HallRepository hallRepository;

    @Transactional
    public Long save(EventsRegisterRequestDto requestDto) {
        Long hallId = requestDto.getHallId();
        Hall hall = hallRepository.findById(hallId)
                .orElseThrow(() -> new IllegalArgumentException("해당 행사장이 존재하지 않습니다. id =" + hallId));
        return eventsRepository.save(requestDto.toEntity(hall)).getId();
    }
}
