package com.dunowljj.book.web.dto.events.hall;

import com.dunowljj.book.domain.events.hall.Hall;
import lombok.Builder;
import lombok.Getter;

@Getter
public class HallUpdateResponseDto {

    private Long id;
    private String name;
    private Long capacity;

    @Builder
    public HallUpdateResponseDto(Hall entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.capacity = entity.getCapacity();
    }
}
