/*
package com.dunowljj.book.web.events.ticket;

import com.dunowljj.book.domain.events.event.Event;
import com.dunowljj.book.domain.events.event.EventRegistration;
import com.dunowljj.book.domain.events.event.EventRegistrationRepository;
import com.dunowljj.book.domain.events.event.EventRepository;
import com.dunowljj.book.domain.events.hall.Hall;
import com.dunowljj.book.domain.events.hall.HallRepository;
import com.dunowljj.book.domain.events.ticket.TicketReservationRepository;
import com.dunowljj.book.domain.user.Member;
import com.dunowljj.book.service.events.ticket.TicketReservationService;
import com.dunowljj.book.web.dto.events.ticket.TicketReserveRequestDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TicketApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TicketReservationRepository ticketReservationRepository;

    @Autowired
    private HallRepository hallRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventRegistrationRepository registrationRepository;
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;


    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @BeforeEach
    void fk_setUp() {
        // ?????? ??????
        String name = "A ?????????";
        Long capacity = 10L;

        Hall hall = Hall.builder()
                .name(name)
                .capacity(capacity)
                .build();
        hallRepository.save(hall);

        // ????????? ??????
        Event event = Event.builder()
                .hall(hall)
                .name("?????????")
                .detail("?????? ?????? IT ??????!")
                .price(1_000_000L)
                .recruitAmount(100L)
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now())
                .recruitStartDate(LocalDateTime.now())
                .recruitEndDate(LocalDateTime.now())
                .field("IT")
                .build();

        eventRepository.save(event);

        // ??????-?????? ??????
        Member member = new Member().builder()
                .id(1L)
                .build();

        EventRegistration registration = EventRegistration.builder()
                .member(member)
                .event(event)
                .build();

        registrationRepository.save(registration);
    }

    @AfterEach
    public void tearDown() throws Exception {
        registrationRepository.deleteAll();
        eventRepository.deleteAll();
        hallRepository.deleteAll();
    }


    @Test
    public void TicketReservation_????????????() throws Exception {
        //given
        Long eventId = 1L;
        Long price = 1_000_000L;
        Long amount = 100L;
        LocalDate reservationDate = LocalDate.now();

        TicketReserveRequestDto requestDto = TicketReserveRequestDto.builder()
                .eventId(eventId)
                .price(price)
                .amount(amount)
                .reservationDate(reservationDate)
                .build();

        //when
        ticketReservationRepository.save(requestDto.toEntity())

        //then
     }
}
*/
