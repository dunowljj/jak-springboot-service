package com.dunowljj.book.domain.hall;


import com.dunowljj.book.domain.BaseTimeEntity;
import com.dunowljj.book.domain.events.Events;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@NoArgsConstructor
@Getter
@Entity
public class Hall extends BaseTimeEntity {
    @Id
    @GeneratedValue
//    @Column(name = "hall_id")
    private Long id;

    @OneToOne(mappedBy = "hall", fetch = LAZY)
    private Events events;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long capacity;

    @Builder
    public Hall(Long id, Events events, String name, Long capacity) {
        this.id = id;
        this.events = events;
        this.name = name;
        this.capacity = capacity;
    }
}
