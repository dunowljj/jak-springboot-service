package com.dunowljj.book.domain.events.event;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("SELECT e FROM Event e ORDER BY e.id DESC")
    List<Event> findAllDESC();
}
