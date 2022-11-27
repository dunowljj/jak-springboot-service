package com.dunowljj.book.service.events.hall;

import com.dunowljj.book.domain.events.hall.Hall;
import com.dunowljj.book.domain.events.hall.HallRepository;
import com.dunowljj.book.web.dto.events.hall.HallSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class HallService {

    private final HallRepository hallRepository;


    public Long save(HallSaveRequestDto requestDto) {
        return hallRepository.save(requestDto.toEntity()).getId();
    }
}
