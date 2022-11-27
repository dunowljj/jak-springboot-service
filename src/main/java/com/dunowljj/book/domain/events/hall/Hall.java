package com.dunowljj.book.domain.events.hall;


import com.dunowljj.book.domain.BaseTimeEntity;
import com.dunowljj.book.domain.events.event.Event;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.*;

@NoArgsConstructor
@Getter
@Entity
public class Hall extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
//    @Column(name = "hall_id")
    private Long id;

    @OneToOne(mappedBy = "hall", fetch = LAZY)
    private Event event;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long capacity;

    @Builder
    public Hall(Long id, Event event, String name, Long capacity) {
        this.id = id;
        this.event = event;
        this.name = name;
        this.capacity = capacity;
    }
}
