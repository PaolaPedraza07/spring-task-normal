package com.epam.springtask.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "tickets")
@XmlAccessorType(XmlAccessType.FIELD)
public class Tickets {

    @XmlElement(name = "ticket")
    private List<TicketImplementation> tickets;

    public List<TicketImplementation> getTickets() {
        return tickets;
    }

    public void setTickets(List<TicketImplementation> tickets) {
        this.tickets = tickets;
    }
}
