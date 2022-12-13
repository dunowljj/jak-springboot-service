package com.dunowljj.book.service.events.hall;

import com.dunowljj.book.domain.events.hall.Hall;
import com.dunowljj.book.domain.events.hall.HallRepository;
import com.dunowljj.book.web.dto.events.hall.HallListResponseDto;
import com.dunowljj.book.web.dto.events.hall.HallSaveRequestDto;
import com.dunowljj.book.web.dto.events.hall.HallUpdateRequestDto;
import com.dunowljj.book.web.dto.events.hall.HallUpdateResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class HallService {

    private final HallRepository hallRepository;


    @Transactional
    public Long save(HallSaveRequestDto requestDto) {
        return hallRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional(readOnly = true)
    public List<HallListResponseDto> findAllDesc() {
        return hallRepository.findAllDESC().stream()
                .map(HallListResponseDto::new)
                .collect(Collectors.toList());
    }

    public HallUpdateResponseDto findById(Long id) {
        // todo: 1line vs 2line?
        Hall entity = hallRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 행사장이 존재하지 않습니다. id =" + id));
        return new HallUpdateResponseDto(entity);
    }

    @Transactional
    public Long update(Long id, HallUpdateRequestDto requestDto) {
        Hall hall = hallRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 행사장이 존재하지 않습니다. id =" + id));
        hall.update(requestDto);
        return id;
    }

    @Transactional
    public void delete(Long id) {
        Hall hall = hallRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 행사장이 존재하지 않습니다. id =" + id));
        hallRepository.delete(hall);
        return;
    }

    public List<Hall> findAllReadyDESC() {
        return hallRepository.findAllReadyDESC();
    }
}
