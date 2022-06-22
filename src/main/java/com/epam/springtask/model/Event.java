package com.epam.springtask.model;

import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by maksym_govorischev.
 */
public interface Event {
    /**
     * Event id. UNIQUE.
     * @return Event Id
     */
    long getId();
    void setId(long id);
    String getTitle();
    void setTitle(String title);
    Date getDate();
    void setDate(Date date);
    double getTicketprice();
    void setTicketprice(double ticketprice);
}
