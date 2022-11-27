package com.dunowljj.book.web.dto.events.hall;

import com.dunowljj.book.domain.events.hall.Hall;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class HallSaveRequestDto {

    private String name;
    private Long capacity;

    public Hall toEntity() {
        return Hall.builder()
                .name(name)
                .capacity(capacity)
                .build();
    }

    @Builder
    public HallSaveRequestDto(String name, Long capacity) {
        this.name = name;
        this.capacity = capacity;
    }
}
