package com.dunowljj.book.web.dto.events.hall;

import com.dunowljj.book.domain.events.hall.Hall;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class HallListResponseDto {
    private Long id;
    private String name;
    private Long capacity;

    private LocalDateTime modifiedDate;

    private LocalDateTime createdDate;

    public HallListResponseDto(Hall entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.capacity = entity.getCapacity();
        this.modifiedDate = entity.getModifiedDate();
        this.createdDate = entity.getCreatedDate();
    }
}
