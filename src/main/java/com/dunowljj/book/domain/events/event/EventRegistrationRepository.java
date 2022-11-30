package com.dunowljj.book.domain.events.event;


import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRegistrationRepository extends JpaRepository<EventRegistration, Long> {

}
