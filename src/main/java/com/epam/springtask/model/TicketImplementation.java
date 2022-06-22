package com.epam.springtask.model;

import org.springframework.stereotype.Component;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ticket")
@XmlAccessorType(XmlAccessType.FIELD)
@Component
@Entity
@Table(name = "ticket")
public class TicketImplementation implements Ticket{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private long id;
    @Column
    private long eventId;
    @Column
    private long userId;
    @Column
    @Enumerated(EnumType.STRING)
    private Category category;
    @Column
    private int place;

    public TicketImplementation(long eventId, long userId, Category category, int place){
        this.eventId = eventId;
        this.userId = userId;
        this.category = category;
        this.place = place;
    }

    public TicketImplementation(){

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
    public long getEventId() {
        return eventId;
    }

    @Override
    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    @Override
    public long getUserId() {
        return userId;
    }

    @Override
    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public Category getCategory() {
        return category;
    }

    @Override
    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public int getPlace() {
        return place;
    }

    @Override
    public void setPlace(int place) {
        this.place = place;
    }
}
