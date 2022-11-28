package com.dunowljj.book.domain.events.event;

import com.dunowljj.book.domain.events.hall.Hall;
import com.dunowljj.book.domain.events.hall.HallRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class EventRepositoryTest {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private HallRepository hallRepository;

    // deleteAll 안하면 다음 테스트 영향
    @AfterEach
    public void tearDown() throws Exception {
        eventRepository.deleteAll();
        hallRepository.deleteAll();
    }

    //todo : auto-increment 테스트 개선2
    @Test
    public void 이벤트등록_불러오기() throws Exception {
        //given
        Hall hall = Hall.builder()
                .name("hallA")
                .capacity(10L)
                .build();
        hallRepository.save(hall);

        String name = "name";
        String detail = "detail";
        Long price = 1000L;
        Long recruitAmount = 10L;
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = LocalDateTime.now();
        LocalDateTime recruitStartDate = LocalDateTime.now();
        LocalDateTime recruitEndDate = LocalDateTime.now();
        String field = "field";

        Event event = Event.builder()
                .name(name)
                .detail(detail)
                .price(price)
                .recruitAmount(recruitAmount)
                .startDate(startDate)
                .endDate(endDate)
                .recruitStartDate(recruitStartDate)
                .recruitEndDate(recruitEndDate)
                .field(field)
                .hall(hall)
                .build();
        //when
        eventRepository.save(event);
        List<Event> eventList = eventRepository.findAll();

        //then
        assertThat(eventList.get(0).getName()).isEqualTo(name);
        assertThat(eventList.get(0).getDetail()).isEqualTo(detail);
        assertThat(eventList.get(0).getRecruitAmount()).isEqualTo(recruitAmount);
        assertThat(eventList.get(0).getStartDate()).isEqualTo(startDate);
        assertThat(eventList.get(0).getEndDate()).isEqualTo(endDate);
        assertThat(eventList.get(0).getRecruitStartDate()).isEqualTo(recruitStartDate);
        assertThat(eventList.get(0).getRecruitEndDate()).isEqualTo(recruitEndDate);
        assertThat(eventList.get(0).getField()).isEqualTo(field);

     }
}
