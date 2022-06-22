package com.epam.springtask.service;

import com.epam.springtask.dao.EventDAO;
import com.epam.springtask.model.Event;
import com.epam.springtask.model.EventImplementation;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public class EventService {
    EventDAO eventDAO;

    public EventService(EventDAO eventDAO){
        this.eventDAO = eventDAO;
    }

    public Event getEventById(long eventId){
        Event event = eventDAO.findById(eventId).get();
        return event;
    }

    public List<Event> getEventsByTitle(String title){
        List<Event> eventList = eventDAO.findAllByTitle(title);
        return eventList;
    }

    public List<Event> getEventsForDay(Date day){
        List<Event> eventList = eventDAO.findAllByDate(day);
        return eventList;
    }

    public Event createEvent(Event event){
        return eventDAO.save((EventImplementation) event);
    }
    public Event updateEvent(Event event){
        return eventDAO.save((EventImplementation)event);
    }
    public boolean deleteEvent(long eventId) {
        try {
            eventDAO.deleteById(eventId);
            return true;
        }catch (Exception e){
            return false;
        }
    }

}
