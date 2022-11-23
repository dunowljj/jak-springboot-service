package com.dunowljj.book.domain.events;

import com.dunowljj.book.domain.BaseTimeEntity;
import com.dunowljj.book.domain.user.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class EventsRegistration extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
//    @Column(name = "events_registration_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "events_id")
    private Events events;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public EventsRegistration(Long id, Events events, Member member) {
        this.id = id;
        this.events = events;
        this.member = member;
    }
}
