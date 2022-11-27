package com.dunowljj.book.web.dto.events.event;

import com.dunowljj.book.domain.events.event.Event;
import com.dunowljj.book.domain.events.hall.Hall;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class EventSaveRequestDto {

    private String name;
    private Long hallId;
    private String detail;
    private Long price;
    private Long recruitAmount;

    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime recruitStartDate;
    private LocalDateTime recruitEndDate;
    private String field;

    @Builder
    public EventSaveRequestDto(String name, Long hallId, String detail, Long price, Long recruitAmount, String field, LocalDateTime startDate, LocalDateTime endDate, LocalDateTime recruitStartDate, LocalDateTime recruitEndDate) {
        this.name = name;
        this.hallId = hallId;
        this.detail = detail;
        this.price = price;
        this.recruitAmount = recruitAmount;
        this.field = field;
        this.startDate = startDate;
        this.endDate = endDate;
        this.recruitStartDate = recruitStartDate;
        this.recruitEndDate = recruitEndDate;
    }

    public Event toEntity(Hall hall) {

        //todo : hallID를 어떻게 처리할 것인가?
        return Event.builder()
                .hall(hall)
                .name(name)
                .detail(detail)
                .price(price)
                .recruitAmount(recruitAmount)
                .field(field)
                .startDate(startDate)
                .endDate(endDate)
                .recruitStartDate(recruitStartDate)
                .recruitEndDate(recruitEndDate)
                .build();
    }
}
