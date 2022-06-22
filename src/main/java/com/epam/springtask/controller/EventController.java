package com.epam.springtask.controller;

import com.epam.springtask.facade.BookingFacadeImpl;
import com.epam.springtask.model.Event;
import com.epam.springtask.model.EventImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.Date;
import java.util.List;

//->  localhost:8080/event/id/1

@RestController
@RequestMapping("event")
public class EventController {

    @Autowired
    BookingFacadeImpl bookingFacade;

    /**
     * This is a controller to get an Event by its id
     *
     * @param eventId
     * @return ModelAndView
     */
    @GetMapping("/id/{eventId}")
    public Event getEventById(@PathVariable Long eventId){
        //ModelAndView thymeleaf = new ModelAndView("event");
        Event eventResponse = bookingFacade.getEventById(eventId);
        //thymeleaf.addObject("event", eventResponse);
        return eventResponse;
    }

    /**
     * This is a controller to get a list of Events by Title
     *
     * @param title
     * @return ModelAndView
     */
    @GetMapping("/title/{title}")
    public ModelAndView getEventsByTitle(@PathVariable String title){
        ModelAndView thymeleaf = new ModelAndView("eventList");
        List<Event> eventList = bookingFacade.getEventsByTitle(title, 1,1);
        thymeleaf.addObject("eventList", eventList);
        return thymeleaf;
    }

    /**
     * This is a controller to get a list of Events by day
     *
     * @param day
     * @return ModelAndView
     */
    @GetMapping("/day")
    public ModelAndView getEventsByDay(@RequestParam Date day){
        ModelAndView thymeleaf = new ModelAndView("eventList");
        List<Event> eventList = bookingFacade.getEventsForDay(day, 1,1);
        thymeleaf.addObject("eventList", eventList);
        return thymeleaf;
    }

    /**
     * This controller creates an Event
     *
     * @param event
     * @return The event created
     */
    @PostMapping("/create")
    public Event createEvent(@RequestBody EventImplementation event){
        Event eventResponse = bookingFacade.createEvent(event);
        return eventResponse;
    }

    /**
     * This controller updates an Event
     *
     * @param event
     * @return The event updated
     */
    @PutMapping("/update")
    public Event updateEvent(@RequestBody EventImplementation event){
        Event eventResponse = bookingFacade.updateEvent(event);
        return eventResponse;
    }

    /**
     * This controller deletes an Event
     *
     * @param eventId
     * @return String
     */
    @DeleteMapping("/delete/{eventId}")
    public String deleteEvent(@PathVariable Long eventId){
        boolean deleted = bookingFacade.deleteEvent(eventId);
        if(deleted){
            return "The event "+eventId+" was deleted successfully";
        }
        return "The event "+eventId+" doesn't exist";
    }
}