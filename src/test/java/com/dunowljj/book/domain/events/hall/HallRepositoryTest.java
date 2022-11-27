package com.dunowljj.book.domain.events.hall;

import com.dunowljj.book.web.dto.events.hall.HallSaveRequestDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class HallRepositoryTest {

    @Autowired
    private HallRepository hallRepository;

    @AfterEach
    public void cleanup() {
        hallRepository.deleteAll();
    }

    @Test
    public void 행사장등록_불러오기() throws Exception {
        //given
        String name = "테스트 행사명";
        Long capacity = 100L;

        Hall hall = Hall.builder()
                .name(name)
                .capacity(capacity)
                .build();

        hallRepository.save(hall);

        //when
        List<Hall> HallList = hallRepository.findAll();

        //then
        assertThat(HallList.get(0).getName()).isEqualTo(name);
        assertThat(HallList.get(0).getCapacity()).isEqualTo(capacity);
     }
}
