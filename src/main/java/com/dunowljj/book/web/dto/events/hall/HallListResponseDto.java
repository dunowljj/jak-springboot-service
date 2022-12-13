package com.dunowljj.book.web.dto.events.hall;

import com.dunowljj.book.domain.events.hall.Hall;
import com.dunowljj.book.domain.events.hall.RentalStatus;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class HallListResponseDto {
    private Long id;
    private String name;
    private Long capacity;
    private LocalDateTime modifiedDate;
    private LocalDateTime createdDate;
    private RentalStatus rentalStatus;

    public HallListResponseDto(Hall entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.capacity = entity.getCapacity();
        this.modifiedDate = entity.getModifiedDate();
        this.createdDate = entity.getCreatedDate();
        this.rentalStatus = entity.getRentalStatus();
    }
}
