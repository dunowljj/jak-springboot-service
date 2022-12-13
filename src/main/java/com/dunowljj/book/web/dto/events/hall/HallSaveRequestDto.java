package com.dunowljj.book.web.dto.events.hall;

import com.dunowljj.book.domain.events.hall.Hall;
import com.dunowljj.book.domain.events.hall.RentalStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class HallSaveRequestDto {

    private String name;
    private Long capacity;
    private RentalStatus rentalStatus;

    public Hall toEntity() {
        return Hall.builder()
                .name(name)
                .capacity(capacity)
                .rentalStatus(rentalStatus)
                .build();
    }

    @Builder
    public HallSaveRequestDto(String name, Long capacity, RentalStatus rentalStatus) {
        this.name = name;
        this.capacity = capacity;
        this.rentalStatus = rentalStatus;
    }
}
