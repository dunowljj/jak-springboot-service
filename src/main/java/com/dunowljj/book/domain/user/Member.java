package com.dunowljj.book.domain.user;

import com.dunowljj.book.domain.BaseTimeEntity;
import com.dunowljj.book.domain.events.EventsRegistration;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "member")
    private List<EventsRegistration> eventsRegistrationList = new ArrayList<>();

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String picture;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    // todo : 생년월일, 가입날짜, 성별 등 추후에 추가

    @Builder
    public Member(Long id, List<EventsRegistration> eventsRegistrationList, String name, String email, String picture, Role role) {
        this.id = id;
        this.eventsRegistrationList = eventsRegistrationList;
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }

    public Member update(String name, String picture) {
        this.name = name;
        this.picture = picture;

        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}
