package com.epam.springtask.dao;

import com.epam.springtask.model.Event;
import com.epam.springtask.model.EventImplementation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;

@Repository
public interface EventDAO extends JpaRepository<EventImplementation, Long> {
    List<Event> findAllByTitle(String title);
    List<Event> findAllByDate(Date date);
}
