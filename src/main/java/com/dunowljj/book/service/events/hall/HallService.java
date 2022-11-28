package com.dunowljj.book.service.events.hall;

import com.dunowljj.book.domain.events.hall.Hall;
import com.dunowljj.book.domain.events.hall.HallRepository;
import com.dunowljj.book.domain.posts.Posts;
import com.dunowljj.book.web.dto.events.hall.HallListResponseDto;
import com.dunowljj.book.web.dto.events.hall.HallSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class HallService {

    private final HallRepository hallRepository;


    public Long save(HallSaveRequestDto requestDto) {
        return hallRepository.save(requestDto.toEntity()).getId();
    }

    public List<HallListResponseDto> findAllDesc() {
        return hallRepository.findAllDesc().stream()
                .map(HallListResponseDto::new)
                .collect(Collectors.toList());
    }

}
