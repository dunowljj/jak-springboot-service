package com.dunowljj.book.domain.events.event;

import com.dunowljj.book.domain.BaseTimeEntity;
import com.dunowljj.book.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

@NoArgsConstructor(access = PROTECTED)
@Getter
@Entity
public class EventRegistration extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
//    @Column(name = "events_registration_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public EventRegistration(Long id, Event event, User user) {
        this.id = id;
        this.event = event;
        this.user = user;
    }

}
