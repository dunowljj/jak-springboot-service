package com.dunowljj.book.web.dto.events.hall;

import com.dunowljj.book.domain.events.hall.Hall;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class HallUpdateRequestDto {
    private String name;
    private Long capacity;

    @Builder
    public HallUpdateRequestDto(Hall entity) {
        this.name = entity.getName();
        this.capacity = entity.getCapacity();
    }
}
