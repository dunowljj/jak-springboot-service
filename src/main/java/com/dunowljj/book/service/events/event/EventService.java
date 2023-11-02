package com.dunowljj.book.service.events.event;

import com.dunowljj.book.security.oauth.SessionUser;
import com.dunowljj.book.domain.events.event.Event;
import com.dunowljj.book.domain.events.event.EventRegistration;
import com.dunowljj.book.domain.events.event.EventRegistrationRepository;
import com.dunowljj.book.domain.events.event.EventRepository;
import com.dunowljj.book.domain.events.hall.Hall;
import com.dunowljj.book.domain.events.hall.HallRepository;
import com.dunowljj.book.domain.user.User;
import com.dunowljj.book.domain.user.UserRespository;
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
    private final EventRegistrationRepository registrationRepository;

    private final UserRespository userRespository;
    private final HallRepository hallRepository;

    @Transactional
    public Long save(EventSaveRequestDto requestDto, SessionUser sessionUser) {
        User user = userRespository.findByEmail(sessionUser.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("로그인 후에 이용해 주세요"));

        Long hallId = requestDto.getHallId();
        Hall hall = hallRepository.findById(hallId)
                .orElseThrow(() -> new IllegalArgumentException("해당 행사장이 존재하지 않습니다. id =" + hallId));

        if (hall.isUSING()) throw new IllegalArgumentException("이미 사용중인 행사장입니다. id=" + hallId);
        if (hall.isSUSPEND()) throw new IllegalArgumentException("사용 중지된 행사장입니다. id=" + hallId);

        Event event = requestDto.toEntity(hall);

        EventRegistration eventRegistration = EventRegistration.builder()
                .user(user)
                .event(event)
                .build();

        registrationRepository.save(eventRegistration);
        return eventRepository.save(event).getId();
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

        if (hall.isUSING()) throw new IllegalArgumentException("이미 사용중인 행사장입니다. id=" + hallId);
        if (hall.isSUSPEND()) throw new IllegalArgumentException("사용 중지된 행사장입니다. id=" + hallId);

        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 행사가 존재하지 않습니다. id =" + id));

        event.update(requestDto, hall);
        return id;
    }

    @Transactional
    public void delete(Long id) {
        EventRegistration eventRegistration = registrationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 행사등록이 존재하지 않습니다."));

        registrationRepository.delete(eventRegistration);

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
