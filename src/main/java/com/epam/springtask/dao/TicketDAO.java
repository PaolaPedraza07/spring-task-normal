package com.epam.springtask.dao;

import com.epam.springtask.model.Ticket;
import com.epam.springtask.model.TicketImplementation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TicketDAO extends JpaRepository<TicketImplementation, Long> {
    List<Ticket> findAllByUserId(Long userId);
    List<Ticket> findAllByEventId(Long eventId);
}
