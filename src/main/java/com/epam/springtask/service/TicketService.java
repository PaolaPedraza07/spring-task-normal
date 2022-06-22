package com.epam.springtask.service;

import com.epam.springtask.dao.EventDAO;
import com.epam.springtask.dao.TicketDAO;
import com.epam.springtask.dao.UserAccountDao;
import com.epam.springtask.model.*;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService {
    TicketDAO ticketDAO;
    UserAccountDao userAccountDao;
    EventDAO eventDAO;

    public TicketService(TicketDAO ticketDAO, UserAccountDao userAccountDao, EventDAO eventDAO){
        this.ticketDAO = ticketDAO;
        this.userAccountDao = userAccountDao;
        this.eventDAO = eventDAO;
    }

    public Ticket bookTicket(long userId, long eventId, int place, Ticket.Category category) {
        Ticket ticket = new TicketImplementation(eventId, userId, category, place);
        if(ticketDAO.findAll().stream().anyMatch(t -> t.getPlace() == place && t.getUserId() == userId)){
            throw new IllegalStateException("This place has already been bocked");
        }
        Optional<UserAccount> userAccount = userAccountDao.findById(userId);
        Optional<EventImplementation> event = eventDAO.findById(eventId);
        if(userAccount.isPresent() && event.isPresent()) {
                if(userAccount.get().getAmount() >= event.get().getTicketprice()){
                    double currentAmount = userAccount.get().getAmount()-event.get().getTicketprice();
                    userAccount.get().setAmount(currentAmount);
                    TicketImplementation ticketResponse = ticketDAO.save((TicketImplementation)ticket);
                    if(ticketResponse!=null){
                        userAccountDao.save(userAccount.get());
                    }
                    return ticketResponse;
                }else {
                    throw new IllegalStateException("The user account doesn't have enough money to book this event");
                }
        }else {
            throw new IllegalStateException("The event or the user account doesn't exist");
        }
    }

    public List<Ticket> getBookedTickets(User user, int pageSize, int pageNum) {
        return ticketDAO.findAllByUserId(user.getId());
    }

    public List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum) {
        return ticketDAO.findAllByEventId(event.getId());
    }

    public boolean cancelTicket(long ticketId) {
        try {
            ticketDAO.deleteById(ticketId);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public void bookListTicket (List<TicketImplementation> list){
        List<TicketImplementation> ticketsSaved = ticketDAO.saveAll(list);
        if (ticketsSaved.size() == 0){
            throw new IllegalStateException("Something happened at the moment to stored the tickets");
        }
    }
}
