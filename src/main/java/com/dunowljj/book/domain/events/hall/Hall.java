package com.dunowljj.book.domain.events.hall;

import com.dunowljj.book.domain.BaseTimeEntity;
import com.dunowljj.book.domain.events.event.Event;
import com.dunowljj.book.web.dto.events.hall.HallUpdateRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
// todo: 행사장 등록에 대해 멤버 관련한 연관관계 필요
@NoArgsConstructor
@Getter
@Entity
public class Hall extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
//    @Column(name = "hall_id")
    private Long id;

    @OneToMany(mappedBy = "hall", fetch = LAZY)
    private List<Event> event = new ArrayList<>();

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long capacity;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RentalStatus rentalStatus;

    @Builder
    public Hall(Long id, List<Event> event, String name, Long capacity, RentalStatus rentalStatus) {
        this.id = id;
        this.event = event;
        this.name = name;
        this.capacity = capacity;
        this.rentalStatus = rentalStatus;
    }

    public void update(HallUpdateRequestDto requestDto) {
        this.name = requestDto.getName();
        this.capacity = requestDto.getCapacity();
    }

    public boolean isUSING() {
        return rentalStatus == RentalStatus.USING;
    }

    public boolean isSUSPEND() {
        return rentalStatus == RentalStatus.SUSPEND;
    }
}
