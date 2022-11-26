package com.dunowljj.book.web.events;

import com.dunowljj.book.domain.events.Events;
import com.dunowljj.book.domain.events.EventsRepository;
import com.dunowljj.book.domain.hall.Hall;
import com.dunowljj.book.domain.hall.HallRepository;
import com.dunowljj.book.web.dto.events.EventsRegisterRequestDto;
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

import static org.assertj.core.api.Assertions.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EventsApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private EventsRepository eventsRepository;

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
    public void 행사장_setUp() {
        String name = "A 행사장";
        Long capacity = 10L;

        Hall hall = Hall.builder()
                .name(name)
                .capacity(capacity)
                .build();
        hallRepository.save(hall);
    }

    @AfterEach
    public void tearDown() throws Exception {
        eventsRepository.deleteAll();
    }

    @WithMockUser(username = "USER")
    @Test
    public void Events_등록된다() throws Exception {
        //when
        String name = "name";
        Long hallId = 1L;
        String detail ="detail";
        Long price = 1_000L;
        Long recruitAmount = 100L;
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate =LocalDateTime.now();
        LocalDateTime recruitStartDate =LocalDateTime.now();
        LocalDateTime recruitEndDate =LocalDateTime.now();
        String field ="field";

        EventsRegisterRequestDto requestDto = EventsRegisterRequestDto.builder()
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

        String url = "http://localhost:" + port + "/events/new";

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        mvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk());

        //then
        List<Events> all  = eventsRepository.findAll();
        assertThat(all.get(0).getName()).isEqualTo(name);
        assertThat(all.get(0).getField()).isEqualTo(field);
        assertThat(all.get(0).getHall().getId()).isEqualTo(hallId);
     }

}
