package com.epam.springtask.storage;

import com.epam.springtask.model.Event;
import com.epam.springtask.model.Ticket;
import com.epam.springtask.model.User;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

@Component
public class AppStorage {

    public Map<String, Event> events = new HashMap<>();

    public Map<String, Ticket> tickets = new HashMap<>();

    public Map<String, User> users = new HashMap<>();

    public void setEvents(Map<String, Event> events) {
        this.events = events;
    }

    public void setTickets(Map<String, Ticket> tickets) {
        this.tickets = tickets;
    }

    public void setUsers(Map<String, User> users) {
        this.users = users;
    }
}
