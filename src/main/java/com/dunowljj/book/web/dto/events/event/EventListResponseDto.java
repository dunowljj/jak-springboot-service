package com.dunowljj.book.web.dto.events.event;

import com.dunowljj.book.domain.events.event.Event;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class EventListResponseDto {

    private Long id;
    private String name;
    private Long hallId;
    private String field;
    private String detail;
    private Long price;
    private Long recruitAmount;
    private LocalDateTime recruitStartDate;
    private LocalDateTime recruitEndDate;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public EventListResponseDto(Event entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.hallId = entity.getHall().getId();
        this.field = entity.getField();
        this.detail = entity.getDetail();
        this.price = entity.getPrice();
        this.recruitAmount = entity.getRecruitAmount();
        this.recruitStartDate = entity.getRecruitStartDate();
        this.recruitEndDate = entity.getRecruitEndDate();
        this.startDate = entity.getStartDate();
        this.endDate = entity.getEndDate();
        this.createdDate = entity.getCreatedDate();
        this.modifiedDate = entity.getModifiedDate();
    }

}
