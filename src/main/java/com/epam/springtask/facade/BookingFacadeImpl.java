package com.epam.springtask.facade;

import com.epam.springtask.SpringTaskApplication;
import com.epam.springtask.model.*;
import com.epam.springtask.service.EventService;
import com.epam.springtask.service.TicketService;
import com.epam.springtask.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.List;

@Component
public class BookingFacadeImpl implements BookingFacade{

    EventService eventService;
    TicketService ticketService;
    UserService userService;
    Logger logger = LoggerFactory.getLogger(SpringTaskApplication.class);

    public BookingFacadeImpl(EventService eventService, TicketService ticketService, UserService userService) {
        this.eventService = eventService;
        this.ticketService = ticketService;
        this.userService = userService;
    }

    @Override
    public Event getEventById(long eventId) { // Done
        logger.info("method getEventById");
        Event event = eventService.getEventById(eventId);
        return event;
    }

    @Override
    public List<Event> getEventsByTitle(String title, int pageSize, int pageNum) { // Done
        logger.info("method getEventsByTitle");
        List<Event> eventList = eventService.getEventsByTitle(title);
        return eventList;
    }

    @Override
    public List<Event> getEventsForDay(Date day, int pageSize, int pageNum) { //Done
        logger.info("method getEventsForDay");
        List<Event> eventList = eventService.getEventsForDay(day);
        return eventList;
    }

    @Override
    public Event createEvent(Event event) { //Done
        logger.info("method createEvent");
        return eventService.createEvent(event);

    }

    @Override
    public Event updateEvent(Event event) { //Done
        logger.info("method updateEvent");
        return eventService.updateEvent(event);
    }

    @Override
    public boolean deleteEvent(long eventId) { //Done
        logger.info("method deleteEvent");
        return eventService.deleteEvent(eventId);
    }

    @Override
    public User getUserById(long userId) { //Done
        logger.info("method getUserById");
        User user = userService.getUsertById(userId);
        return user;
    }

    @Override
    public User getUserByEmail(String email) { //Done
        logger.info("method getUserByEmail");
        User user = userService.getUserByEmail(email);
        return user;
    }

    @Override
    public List<User> getUsersByName(String name, int pageSize, int pageNum) { //Done
        logger.info("method getUsersByName");
        List<User> userList = userService.getUsersByName(name);
        return userList;
    }

    @Override
    public User createUser(User user) { //Done
        logger.info("method createUser");
        return userService.createUser(user);
    }

    @Override
    public User updateUser(User user) { //Done
        logger.info("method updateUser");
        return userService.updateUser(user);
    }

    @Override
    public boolean deleteUser(long userId) { //Done
        logger.info("method deleteUser");
        return userService.deleteUser(userId);
    }

    @Override
    public Ticket bookTicket(long userId, long eventId, int place, Ticket.Category category) { //Done
        logger.info("method bookTicket");
        return ticketService.bookTicket( userId,  eventId,  place,  category);
    }

    @Override
    public List<Ticket> getBookedTickets(User user, int pageSize, int pageNum) {
        logger.info("method getBookedTickets");
        return ticketService.getBookedTickets(user, pageSize, pageNum);
    }

    @Override
    public List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum) {
        logger.info("method getBookedTickets");
        return ticketService.getBookedTickets(event, pageSize, pageNum);
    }

    @Override
    public boolean cancelTicket(long ticketId) { //Done
        logger.info("method cancelTicket");
        return ticketService.cancelTicket(ticketId);
    }

    public void bookListTicket(List<TicketImplementation> list){
        ticketService.bookListTicket(list);
    }

    public UserAccount refillAccount(long userId, double amount){
        return userService.refillAccountByUserId(userId, amount);
    }
}
