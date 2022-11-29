package com.dunowljj.book.web.dto.events.event;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class EventUpdateRequestDto {
    private Long id;
    private String name;
    private Long hallId;
    private String field;
    private String detail;
    private Long price;
    private Long recruitAmount;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime recruitStartDate;
    private LocalDateTime recruitEndDate;
}
