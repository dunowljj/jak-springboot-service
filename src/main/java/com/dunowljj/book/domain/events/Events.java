package com.dunowljj.book.domain.events;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.*;

@NoArgsConstructor
@Getter
@Entity
public class Events {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    // todo
    private Long hallId;

    @Column(length = 500, nullable = false)
    private String name;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String detail;

    @Column(nullable = false)
    private Long price;

    @Column(nullable = false)
    private Long recruitAmount;

    @Column(nullable = false)
    private LocalDate start;

    @Column(nullable = false)
    private LocalDate end;

    @Column(nullable = false)
    private LocalDateTime startRecruit;

    @Column(nullable = false)
    private LocalDateTime endRecruit;

    // todo : @Embeddable 사용? 고정 값
    @Enumerated(EnumType.STRING)
    private Hall type;

    // todo
    @Enumerated(EnumType.STRING)
    private Field field;


    private Long hitCount;

    @Builder
    public Events(Long id, Long hallId, String name, String detail, Long price, Long recruitAmount,
                  LocalDate start, LocalDate end, LocalDateTime startRecruit, LocalDateTime endRecruit,
                  Hall type, Field field,
                  Long hitCount) {
        this.id = id;
        this.hallId = hallId;
        this.name = name;
        this.detail = detail;
        this.price = price;
        this.recruitAmount = recruitAmount;
        this.start = start;
        this.end = end;
        this.startRecruit = startRecruit;
        this.endRecruit = endRecruit;
        this.type = type;
        this.field = field;
        this.hitCount = hitCount;
    }
}
