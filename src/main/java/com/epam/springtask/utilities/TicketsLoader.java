package com.epam.springtask.utilities;

import com.epam.springtask.model.Ticket;
import com.epam.springtask.model.TicketImplementation;
import com.epam.springtask.model.Tickets;
import org.springframework.stereotype.Component;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

@Component
public class TicketsLoader {
    public List<TicketImplementation> loadTickets(String path) throws JAXBException
    {
        JAXBContext jaxbContext = JAXBContext.newInstance(Tickets.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        Tickets tickets = (Tickets) jaxbUnmarshaller.unmarshal( new File(path) );

        for(Ticket emp : tickets.getTickets())
        {
            System.out.println(emp.toString());
        }
        return tickets.getTickets();
    }
}
