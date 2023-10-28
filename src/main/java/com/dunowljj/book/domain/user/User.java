package com.dunowljj.book.domain.user;

import com.dunowljj.book.domain.BaseTimeEntity;
import com.dunowljj.book.domain.events.event.EventRegistration;
import com.dunowljj.book.domain.events.ticket.TicketReservation;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "users")
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @OneToMany(mappedBy = "user")
    private List<EventRegistration> eventRegistrationList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<TicketReservation> ticketReservations = new ArrayList<>();

    @Column(nullable = false)
    private String name;

    private String nickname;

    private String password;

    @Column(nullable = false)
    private String email;

    @Column
    private String picture;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;


    @Builder
    public User(Long id, List<EventRegistration> eventRegistrationList, String name, String nickname, String password, String email, String picture, Role role) {
        this.id = id;
        this.eventRegistrationList = eventRegistrationList;
        this.name = name;
        this.nickname = nickname;
        this.password = password;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }

    // todo : 생년월일, 가입날짜, 성별 등 추후에 추가

    @Builder
    public User(Long id, List<EventRegistration> eventRegistrationList, List<TicketReservation> ticketReservations, String name, String nickname, String password, String email, String picture, Role role) {
        this.id = id;
        this.eventRegistrationList = eventRegistrationList;
        this.ticketReservations = ticketReservations;
        this.name = name;
        this.nickname = nickname;
        this.password = password;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }

    public User update(String name, String picture) {
        this.name = name;
        this.picture = picture;
        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}
