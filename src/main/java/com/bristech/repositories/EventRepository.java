package com.bristech.repositories;


import com.bristech.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@SuppressWarnings("SpringDataMethodInconsistencyInspection")
public interface EventRepository extends JpaRepository<Event, Long>{

    List<Event> findByStatus(String status);

}
