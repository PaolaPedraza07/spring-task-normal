package com.epam.springtask.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "event")
public class EventImplementation implements Event{

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private long id;
    @Column
    private String title;
    @Column
    private Date date;
    @Column
    private double ticketprice;

    public EventImplementation(String title, Date date, double ticketprice){
        this.title = title;
        this.date = date;
        this.ticketprice = ticketprice;
    }

    public EventImplementation(){

    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public Date getDate() {
        return date;
    }

    @Override
    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public double getTicketprice() {
        return ticketprice;
    }

    @Override
    public void setTicketprice(double ticketprice) {
        this.ticketprice = ticketprice;
    }
}
