package com.dunowljj.book.domain.events.hall;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HallRepository extends JpaRepository<Hall, Long> {

    // todo : 다른 방식 찾아보기
    @Query("SELECT h FROM Hall h ORDER BY h.id DESC")
    List<Hall> findAllDesc();
}
