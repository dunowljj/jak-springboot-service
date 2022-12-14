package com.dunowljj.book.web.events.event;

import com.dunowljj.book.domain.events.event.Event;
import com.dunowljj.book.domain.events.event.EventRepository;
import com.dunowljj.book.domain.events.hall.Hall;
import com.dunowljj.book.domain.events.hall.HallRepository;
import com.dunowljj.book.web.dto.events.event.EventSaveRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EventApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private HallRepository hallRepository;
    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @BeforeEach
    public void setUp() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @BeforeEach
    public void ?????????_setUp() {
        String name = "A ?????????";
        Long capacity = 10L;

        Hall hall = Hall.builder()
                .name(name)
                .capacity(capacity)
                .build();
        hallRepository.save(hall);
    }

    /**
     * auto-increment ???????????? ?????? ???????????? ?????? ????????? ??????. ????????? ?????? ????????? ?????? ??????????????????.
     * repository??? event??? hall ??? ??? ???????????? ??????, deleteAll ????????? ????????? fk ????????? ????????? ????????? ????????????.
     */
    @AfterEach
    public void tearDown() throws Exception {
        eventRepository.deleteAll();
        hallRepository.deleteAll();
    }

    @WithMockUser(username = "USER")
    @Test
    public void Event_????????????() throws Exception {
        //when
        String name = "name";
        Long hallId = hallRepository.findAll().get(0).getId(); // todo: auto-increment ????????? ?????? 1 : jdbc ?????? ??? ????????? ??? ????????????
        String detail ="detail";
        Long price = 1_000L;
        Long recruitAmount = 100L;
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate =LocalDateTime.now();
        LocalDateTime recruitStartDate =LocalDateTime.now();
        LocalDateTime recruitEndDate =LocalDateTime.now();
        String field ="field";

        EventSaveRequestDto requestDto = EventSaveRequestDto.builder()
                .name(name)
                .hallId(hallId)
                .detail(detail)
                .price(price)
                .recruitAmount(recruitAmount)
                .startDate(startDate)
                .endDate(endDate)
                .recruitStartDate(recruitStartDate)
                .recruitEndDate(recruitEndDate)
                .field(field)
                .build();

        String url = "http://localhost:" + port + "/api/events/event/new";

        // LocalDateTime ????????? ?????? ??????
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        mvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk());

        //then
        List<Event> all  = eventRepository.findAll();
        assertThat(all.get(0).getName()).isEqualTo(name);
        assertThat(all.get(0).getField()).isEqualTo(field);
        assertThat(all.get(0).getHall().getId()).isEqualTo(hallId);
     }

}
